package dxc.microservice.quarkus.domain.model.loan;

import java.util.Date;

import dxc.microservice.quarkus.domain.model.user.UserId;
import dxc.microservice.quarkus.domain.shared.Entity;
import dxc.microservice.quarkus.domain.shared.RootAggregate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Loan implements Entity<Loan>, RootAggregate {
    private LoanId id;

    private int numberOfYears;
    
    private long loanAmount;

    private double annualInterestRate;

    private Date loanDate;

    private UserId userId;

    @Override
    public boolean sameIdentityAs(Loan other) {
        return other != null && this.id.sameValueAs(other.getId());
    }
}
