package dxc.microservice.quarkus.application.repository;

import java.util.List;

import dxc.microservice.quarkus.domain.model.Loan;

public interface ILoanRepository {
    
    List<Loan> getAll();

    Loan findById(String id);

    void save(Loan prop);

    void update(Loan prop);

    void delete(String id);
}
