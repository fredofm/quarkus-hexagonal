package dxc.microservice.quarkus.infrastructure.db.dynamodb.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import dxc.microservice.quarkus.infrastructure.db.dynamodb.dbo.PropEntity;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class DynamoDBConfig {

   /*  @Produces
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient basicClient) {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(basicClient).build();        
    } */

    @Produces
    public DynamoDbTable<PropEntity> propTable(DynamoDbEnhancedClient enhancedClient) {
        TableSchema<PropEntity> tableSchema = TableSchema.fromBean(PropEntity.class);
        return enhancedClient.table("props", tableSchema);
    }
}
