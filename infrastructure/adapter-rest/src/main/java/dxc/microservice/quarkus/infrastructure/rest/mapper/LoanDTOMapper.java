package dxc.microservice.quarkus.infrastructure.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dxc.microservice.quarkus.domain.model.loan.Loan;
import dxc.micrservice.quarkus.infrastructure.rest.dto.LoanDTO;

@Mapper(componentModel = "cdi") 
public interface LoanDTOMapper {
    
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "userId.id", target = "userId")
    public LoanDTO toDto(Loan prop);

    @Mapping(source = "id", target = "id.id")
    @Mapping(source = "userId", target = "userId.id")
    public Loan toDomain(LoanDTO loanDto);
}
