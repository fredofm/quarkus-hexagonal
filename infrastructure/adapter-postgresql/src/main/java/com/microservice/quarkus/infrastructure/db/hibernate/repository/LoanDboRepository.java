package com.microservice.quarkus.infrastructure.db.hibernate.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.microservice.quarkus.domain.model.loan.Loan;
import com.microservice.quarkus.domain.ports.spi.LoanRepository;
import com.microservice.quarkus.infrastructure.db.hibernate.dbo.LoanEntity;
import com.microservice.quarkus.infrastructure.db.hibernate.exceptions.DboException;
import com.microservice.quarkus.infrastructure.db.hibernate.mapper.LoanMapper;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class LoanDboRepository implements LoanRepository {

    LoanPanacheRepository repository;

    LoanMapper loanMapper;

    @Override
    public Loan findById(String id) {
        return loanMapper.toDomain(repository.findById(id));
    }

    @Override
    public List<Loan> getAll() {
        return repository.findAll().stream().map(p -> loanMapper.toDomain(p)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(Loan loan) {
        LoanEntity entity = loanMapper.toDbo(loan);
        repository.persistAndFlush(entity);
        loanMapper.updateDomainFromEntity(entity, loan);
    }

    @Override
    @Transactional
    public void update(Loan loan) {        
        LoanEntity entity = repository.findByIdOptional(loan.getId().getId())
                .orElseThrow(() -> new DboException("No loan found for loan Id [%s]", loan.getId()));
        loanMapper.updateEntityFromDomain(loan, entity);
        repository.persist(entity);
        loanMapper.updateDomainFromEntity(entity, loan);        
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public String nextLoanId() {
        return UUID.randomUUID().toString();        
    }
}
