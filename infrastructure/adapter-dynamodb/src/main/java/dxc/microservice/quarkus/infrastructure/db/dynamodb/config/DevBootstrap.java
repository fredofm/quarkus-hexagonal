package dxc.microservice.quarkus.infrastructure.db.dynamodb.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import dxc.microservice.quarkus.infrastructure.db.dynamodb.dbo.PropEntity;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.DescribeTableEnhancedResponse;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.TableDescription;

@ApplicationScoped
@Slf4j
public class DevBootstrap {
    private static final String PROPS_TABLE = "props";

    @Inject
    DynamoDbClient dynamoDbClient;

    void onStart(@Observes StartupEvent ev) {
        log.info("Bootstrapping dynamodb database");

        if (!tableExists(PROPS_TABLE)) {
            DynamoDbTable<PropEntity> propTable = createTable(PROPS_TABLE);

            populateTable(propTable);
        }
    }

    private void populateTable(DynamoDbTable<PropEntity> propTable) {
        log.info("Populating dynamodb table {}", propTable.tableName());

        propTable.putItem(PropEntity.builder().id(1L).name("name 1").running(false).build());
        propTable.putItem(PropEntity.builder().id(2L).name("name 2").running(true).build());
    }

    private DynamoDbTable<PropEntity> createTable(String tableName) {
        log.info("Creating dynamodb table {}", tableName);

        DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient)
                .build();

        TableSchema<PropEntity> tableSchema = TableSchema.fromClass(PropEntity.class);
        DynamoDbTable<PropEntity> propTable = dynamoDbEnhancedClient.table(PROPS_TABLE, tableSchema);
        propTable.createTable();

        dynamoDbClient.waiter().waitUntilTableExists(b -> b.tableName(PROPS_TABLE));

        return propTable;
    }

    private boolean tableExists(String tableName) {
        boolean tableExists = false;
        DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient)
                .build();
        TableSchema<PropEntity> tableSchema = TableSchema.fromClass(PropEntity.class);

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
