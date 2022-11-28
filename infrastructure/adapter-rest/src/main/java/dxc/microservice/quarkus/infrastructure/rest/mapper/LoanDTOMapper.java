package dxc.microservice.quarkus.infrastructure.rest.mapper;

import org.mapstruct.Mapper;

import dxc.microservice.quarkus.domain.model.Loan;
import dxc.micrservice.quarkus.infrastructure.rest.dto.LoanDTO;

@Mapper(componentModel = "cdi") 
public interface LoanDTOMapper {
    
    public LoanDTO toDto(Loan prop);

    public Loan toDomain(LoanDTO loanDto);
}
