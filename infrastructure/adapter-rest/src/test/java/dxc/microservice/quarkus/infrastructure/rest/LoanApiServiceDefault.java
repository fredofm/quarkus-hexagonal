package dxc.microservice.quarkus.infrastructure.rest;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dxc.microservice.quarkus.application.ports.api.LoanAPIService;
import dxc.microservice.quarkus.domain.model.loan.Loan;

@ApplicationScoped
public class LoanApiServiceDefault implements LoanAPIService {

    @Override
    public Loan getLoan(String id) {
        return null;
    }

    @Override
    public List<Loan> getAllLoans() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteLoan(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void create(Loan prop) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Loan prop) {
        // TODO Auto-generated method stub

    }
}
