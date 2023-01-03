package com.microservice.quarkus.infrastructure.db.dynamodb.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.microservice.quarkus.infrastructure.db.dynamodb.dbo.LoanEntity;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@ApplicationScoped
public class DynamoDBConfig {

    @Produces
    public DynamoDbTable<LoanEntity> propTable(DynamoDbEnhancedClient enhancedClient) {
        TableSchema<LoanEntity> tableSchema = TableSchema.fromBean(LoanEntity.class);
        return enhancedClient.table(LoanEntity.TABLE_NAME, tableSchema);
    }
}
