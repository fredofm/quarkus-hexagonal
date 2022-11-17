package dxc.microservice.quarkus.infrastructure.db.dynamodb.dbo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropEntity {

    private Long id;
    private boolean running;
    private String name;

    @DynamoDbPartitionKey    
    public Long getId() {
        return id;        
    }    
}