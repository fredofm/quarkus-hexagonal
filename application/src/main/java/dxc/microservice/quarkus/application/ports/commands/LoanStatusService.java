package dxc.microservice.quarkus.application.ports.commands;

import dxc.microservice.quarkus.domain.model.loan.LoanId;

public interface LoanStatusService {
    
    public void approveLoan(LoanId loanId);

    public void rejectLoan(LoanId loanId);
}
