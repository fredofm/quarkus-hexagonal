package dxc.microservice.quarkus.infrastructure.db.dynamodb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import dxc.microservice.quarkus.domain.model.Loan;
import dxc.microservice.quarkus.infrastructure.db.dynamodb.dbo.LoanEntity;

@Mapper(componentModel = "cdi")
public interface LoanEntityMapper {    
    LoanEntity toDbo(Loan domain);

    Loan toDomain(LoanEntity entity);

    void updateEntityFromDomain(Loan domain, @MappingTarget LoanEntity entity);

    void updateDomainFromEntity(LoanEntity entity, @MappingTarget Loan domain);
}