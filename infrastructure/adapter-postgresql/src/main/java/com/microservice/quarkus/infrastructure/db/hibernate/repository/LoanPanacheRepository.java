package com.microservice.quarkus.infrastructure.db.hibernate.repository;

import javax.enterprise.context.ApplicationScoped;

import com.microservice.quarkus.infrastructure.db.hibernate.dbo.LoanEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class LoanPanacheRepository implements PanacheRepositoryBase<LoanEntity, String> {

}