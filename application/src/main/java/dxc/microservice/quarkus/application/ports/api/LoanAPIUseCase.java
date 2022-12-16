package dxc.microservice.quarkus.application.ports.api;

import java.util.List;

import dxc.microservice.quarkus.domain.model.loan.Loan;

public interface LoanAPIUseCase {

    public Loan getLoan(String id);

    public List<Loan> getAllLoans();
    
    public void deleteLoan(String id);

    public void create(Loan prop);

    public void update(Loan prop);
}
