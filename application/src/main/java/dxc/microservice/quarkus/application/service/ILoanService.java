package dxc.microservice.quarkus.application.service;

import java.util.List;

import dxc.microservice.quarkus.domain.model.Loan;

public interface ILoanService {

    public Loan getLoan(String id);

    public List<Loan> getAllLoans();
    
    public void deleteLoan(String id);

    public void create(Loan prop);

    public void update(Loan prop);
}
