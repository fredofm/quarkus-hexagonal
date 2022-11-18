package dxc.microservice.quarkus.infrastructure.db.dynamodb.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import dxc.microservice.quarkus.infrastructure.db.dynamodb.dbo.PropEntity;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@ApplicationScoped
public class DynamoDBConfig {

    @Produces
    public DynamoDbTable<PropEntity> propTable(DynamoDbEnhancedClient enhancedClient) {
        TableSchema<PropEntity> tableSchema = TableSchema.fromBean(PropEntity.class);
        return enhancedClient.table("props", tableSchema);
    }
}
