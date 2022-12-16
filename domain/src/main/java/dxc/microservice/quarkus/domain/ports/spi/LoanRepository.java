package dxc.microservice.quarkus.domain.ports.spi;

import java.util.List;

import dxc.microservice.quarkus.domain.model.loan.Loan;

public interface LoanRepository {
    
    List<Loan> getAll();

    Loan findById(String id);

    void save(Loan prop);

    void update(Loan prop);

    void delete(String id);

    String nextLoanId();
}
