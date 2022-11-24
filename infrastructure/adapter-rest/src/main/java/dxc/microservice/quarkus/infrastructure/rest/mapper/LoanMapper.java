package dxc.microservice.quarkus.infrastructure.rest.mapper;

import org.mapstruct.Mapper;

import dxc.microservice.quarkus.domain.model.Prop;
import dxc.micrservice.quarkus.infrastructure.rest.dto.LoanDTO;

@Mapper(componentModel = "cdi") 
public interface LoanMapper {
    
    public LoanDTO toDto(Prop prop);

    public Prop toDomain(LoanDTO loanDto);
}
