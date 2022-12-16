package dxc.microservice.quarkus.domain.events;

import dxc.microservice.quarkus.domain.model.loan.Loan;
import dxc.microservice.quarkus.domain.shared.DomainEvent;

class LoanDeletedEvent implements DomainEvent<Loan> {
    private static final String LOAN_DELETED = "LOAN_DELETED";
}