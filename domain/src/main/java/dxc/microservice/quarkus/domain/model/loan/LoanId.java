package dxc.microservice.quarkus.domain.model.loan;

import dxc.microservice.quarkus.domain.shared.ValueObject;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoanId implements ValueObject<LoanId> {

    private String id;

    @Override
    public boolean sameValueAs(LoanId other) {
        return other != null && this.id.equals(other.id);
    }
    
}
