package com.microservice.quarkus.application.ports.commands;

import com.microservice.quarkus.domain.model.loan.LoanId;

public interface LoanStatusService {
    
    public void approveLoan(LoanId loanId);

    public void rejectLoan(LoanId loanId);
}
