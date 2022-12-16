package dxc.microservice.quarkus.domain.events;

import dxc.microservice.quarkus.domain.model.loan.Loan;
import dxc.microservice.quarkus.domain.shared.DomainEvent;

class LoanCreatedEvent implements DomainEvent<Loan> {
    private static final String LOAN_CREATED = "LOAN_CREATED";
}