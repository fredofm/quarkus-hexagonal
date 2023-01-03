package com.microservice.quarkus.application.ports.api;

import java.util.List;

import com.microservice.quarkus.domain.model.loan.Loan;

public interface LoanAPIService {

    public Loan getLoan(String id);

    public List<Loan> getAllLoans();
    
    public void deleteLoan(String id);

    public void create(Loan prop);

    public void update(Loan prop);
}
