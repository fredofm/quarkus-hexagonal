package dxc.microservice.quarkus.infrastructure.db.hibernate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import dxc.microservice.quarkus.domain.model.Loan;
import dxc.microservice.quarkus.infrastructure.db.hibernate.dbo.LoanEntity;

@Mapper(componentModel = "cdi")
public interface LoanMapper {    
    LoanEntity toDbo(Loan domain);

    Loan toDomain(LoanEntity entity);

    void updateEntityFromDomain(Loan domain, @MappingTarget LoanEntity entity);

    void updateDomainFromEntity(LoanEntity entity, @MappingTarget Loan domain);
}