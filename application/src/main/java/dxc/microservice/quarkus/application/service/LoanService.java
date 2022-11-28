package dxc.microservice.quarkus.application.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dxc.microservice.quarkus.application.repository.ILoanRepository;
import dxc.microservice.quarkus.domain.model.Loan;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class LoanService implements ILoanService {

    ILoanRepository loanRepository;

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

        loanRepository.save(loan);
    }

    @Override
    public void update(Loan loan) {
        log.debug("Updating loan: {}", loan);

        loanRepository.update(loan);
    }
}
