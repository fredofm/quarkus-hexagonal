package com.microservice.quarkus.infrastructure.db.dynamodb.dbo;

import java.time.Instant;

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
public class LoanEntity {
    public static final String TABLE_NAME = "loans";

    private String id;

    private Integer numberOfYears;
    
    private Long loanAmount;

    private Double annualInterestRate;

    private Instant loanDate;

    private String userId;

    private String state;

    @DynamoDbPartitionKey    
    public String getId() {
        return id;        
    }    
}