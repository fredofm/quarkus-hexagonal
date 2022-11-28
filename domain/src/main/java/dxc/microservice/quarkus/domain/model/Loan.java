package dxc.microservice.quarkus.domain.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Loan {
    private String id;

    private int numberOfYears;
    
    private long loanAmount;

    private double annualInterestRate;

    private Date loanDate;

    private long userId;
}
