package com.microservice.quarkus.infrastructure.db.dynamodb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.microservice.quarkus.domain.model.loan.Loan;
import com.microservice.quarkus.infrastructure.db.dynamodb.dbo.LoanEntity;

@Mapper(componentModel = "cdi")
public interface LoanEntityMapper {
    
    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "userId.id", target = "userId")
    LoanEntity toDbo(Loan domain);

    @Mapping(source = "id", target = "id.id")
    @Mapping(source = "userId", target = "userId.id")
    Loan toDomain(LoanEntity entity);

    @Mapping(source = "id.id", target = "id")
    @Mapping(source = "userId.id", target = "userId")
    void updateEntityFromDomain(Loan domain, @MappingTarget LoanEntity entity);

    @Mapping(target = "id", ignore =  true)
    @Mapping(target = "userId", expression = "java(UserId.builder().id(entity.getUserId()).build())" )
    void updateDomainFromEntity(LoanEntity entity, @MappingTarget Loan domain);
}