package com.microservice.quarkus.domain.model.loan;

import com.microservice.quarkus.domain.shared.ValueObject;

public enum LoanState implements ValueObject<LoanState> {    
    APPROVED(String.valueOf("APPROVED")), REJECTED(String.valueOf("REJECTED")), PENDING(String.valueOf("PENDING"));

    private String state;

    LoanState (String state) {
        this.state = state;
    }

    public String value() {
        return state;
    }

    @Override
    public boolean sameValueAs(LoanState other) {
        return this.equals(other);
    }
}
