package dxc.microservice.quarkus.infrastructure.db.hibernate.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import dxc.microservice.quarkus.application.repository.ILoanRepository;
import dxc.microservice.quarkus.domain.model.Loan;
import dxc.microservice.quarkus.infrastructure.db.hibernate.dbo.LoanEntity;
import dxc.microservice.quarkus.infrastructure.db.hibernate.exceptions.DboException;
import dxc.microservice.quarkus.infrastructure.db.hibernate.mapper.LoanMapper;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class LoanDboRepository implements ILoanRepository {

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
    public void save(Loan loan) {
        LoanEntity entity = loanMapper.toDbo(loan);
        entity.setId(UUID.randomUUID().toString());
        repository.persistAndFlush(entity);
        loanMapper.updateDomainFromEntity(entity, loan);
    }

    @Override
    public void update(Loan loan) {        
        LoanEntity entity = repository.findByIdOptional(loan.getId())
                .orElseThrow(() -> new DboException("No loan found for loan Id [%s]", loan.getId()));
        loanMapper.updateEntityFromDomain(loan, entity);
        repository.persist(entity);
        loanMapper.updateDomainFromEntity(entity, loan);        
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
