package com.microservice.quarkus.infrastructure.db.dynamodb.config;

import java.time.Instant;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.microservice.quarkus.domain.model.loan.LoanState;
import com.microservice.quarkus.infrastructure.db.dynamodb.dbo.LoanEntity;
import io.quarkus.arc.profile.UnlessBuildProfile;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.DescribeTableEnhancedResponse;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

@ApplicationScoped
@UnlessBuildProfile("prod")
@Slf4j
public class DevBootstrap {
    private static final String LOANS_TABLE = LoanEntity.TABLE_NAME;

    @Inject
    DynamoDbClient dynamoDbClient;

    void onStart(@Observes StartupEvent ev) {
        log.info("Bootstrapping dynamodb database");

        if (!tableExists(LOANS_TABLE)) {
            DynamoDbTable<LoanEntity> loansTable = createTable(LOANS_TABLE);

            populateTable(loansTable);
        }
    }

    private void populateTable(DynamoDbTable<LoanEntity> loansTable) {
        log.info("Populating dynamodb table {}", loansTable.tableName());

        loansTable.putItem(LoanEntity.builder().id(UUID.randomUUID().toString())
                .annualInterestRate(2.15)
                .loanAmount(130000L)
                .numberOfYears(30)
                .loanDate(Instant.now())
                .userId(UUID.randomUUID().toString())
                .state(LoanState.PENDING.value())
                .build());

        loansTable.putItem(LoanEntity.builder().id(UUID.randomUUID().toString())
                .annualInterestRate(2.15)
                .loanAmount(45000L)
                .numberOfYears(15)
                .loanDate(Instant.now())
                .userId(UUID.randomUUID().toString())
                .state(LoanState.PENDING.value())
                .build());
    }

    private DynamoDbTable<LoanEntity> createTable(String tableName) {
        log.info("Creating dynamodb table {}", tableName);

        DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient)
                .build();

        TableSchema<LoanEntity> tableSchema = TableSchema.fromClass(LoanEntity.class);
        DynamoDbTable<LoanEntity> loansTable = dynamoDbEnhancedClient.table(LOANS_TABLE, tableSchema);
        loansTable.createTable();

        dynamoDbClient.waiter().waitUntilTableExists(b -> b.tableName(LOANS_TABLE));

        return loansTable;
    }

    private boolean tableExists(String tableName) {
        boolean tableExists = false;
        DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient)
                .build();
        TableSchema<LoanEntity> tableSchema = TableSchema.fromClass(LoanEntity.class);

        try {
            DescribeTableEnhancedResponse tableDescription = dynamoDbEnhancedClient.table(tableName, tableSchema)
                    .describeTable();
            log.info("Table {} already exists: {}", tableName, tableDescription);

            tableExists = true;
        } catch (ResourceNotFoundException rnfe) {
            log.info("Table {} does not exist", tableName);
        }

        return tableExists;
    }
}
