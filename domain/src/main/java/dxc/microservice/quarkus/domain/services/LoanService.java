package dxc.microservice.quarkus.domain.services;

import dxc.microservice.quarkus.domain.model.loan.Loan;
import dxc.microservice.quarkus.domain.model.loan.LoanId;
import dxc.microservice.quarkus.domain.ports.spi.EventBus;
import dxc.microservice.quarkus.domain.ports.spi.LoanRepository;

public class LoanService {
    EventBus eventBus;
    LoanRepository loanRepository;

    public Loan approveLoan(LoanId loanId) {
        Loan loan = loanRepository.findById(loanId.getId());

        loan.approveLoan();
        
        loanRepository.save(loan);

        eventBus.publish(loan.domainEvents());

        return loan;
    }
}
