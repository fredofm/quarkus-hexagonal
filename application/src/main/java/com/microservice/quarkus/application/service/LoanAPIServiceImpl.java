package com.microservice.quarkus.application.service;

import java.util.List;

import com.microservice.quarkus.application.ports.api.LoanAPIService;
import com.microservice.quarkus.domain.model.loan.Loan;
import com.microservice.quarkus.domain.model.loan.LoanFactory;
import com.microservice.quarkus.domain.ports.spi.EventBus;
import com.microservice.quarkus.domain.ports.spi.LoanRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class LoanAPIServiceImpl implements LoanAPIService {

    LoanRepository loanRepository;
    LoanFactory loanFactory;
    EventBus eventBus;

    public Loan getLoan(String id) {
        log.debug("Getting loan {}", id);
        return loanRepository.findById(id);
    }

    @Override
    public void deleteLoan(String id) {
        log.debug("Deleting loan {}", id);

        loanRepository.delete(id);
    }

    @Override
    public List<Loan> getAllLoans() {
        log.debug("Getting all loans");
        return loanRepository.getAll();
    }

    @Override
    public void create(Loan loan) {
        log.debug("Creating loan: {}", loan);

        Loan newLoan = loanFactory.createLoan(loan);

        loanRepository.save(newLoan);

        eventBus.publish(newLoan.domainEvents());        
    }

    @Override
    public void update(Loan loan) {
        log.debug("Updating loan: {}", loan);

        loanRepository.update(loan);
    }
}
