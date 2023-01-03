package com.microservice.quarkus.infrastructure.db.hibernate.dbo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LoanEntity {
    
    public static final String TABLE_NAME = "loans";

    @Id 
    private String id;

    private Integer numberOfYears;
    
    private Long loanAmount;

    private Double annualInterestRate;

    private Date loanDate;

    private String userId;

    private String state;
}