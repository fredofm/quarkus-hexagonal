package dxc.microservice.quarkus.infrastructure.db.dynamodb.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import dxc.microservice.quarkus.application.repository.ILoanRepository;
import dxc.microservice.quarkus.domain.model.Loan;
import dxc.microservice.quarkus.infrastructure.db.dynamodb.dbo.LoanEntity;
import dxc.microservice.quarkus.infrastructure.db.dynamodb.exceptions.DboException;
import dxc.microservice.quarkus.infrastructure.db.dynamodb.mapper.LoanEntityMapper;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@ApplicationScoped
@AllArgsConstructor
public class LoanDboRepository implements ILoanRepository {

    DynamoDbTable<LoanEntity> loanTable;

    LoanEntityMapper loanMapper;    

    @Override
    public Loan findById(String id) {
        Key partitionKey = Key.builder().partitionValue(id).build();
        return loanMapper.toDomain(loanTable.getItem(partitionKey));
    }

    @Override
    public List<Loan> getAll() {
        return loanTable.scan().items().stream().map(p -> loanMapper.toDomain(p)).collect(Collectors.toList());
    }

    @Override
    public void save(Loan loan) {
        loan.setId(UUID.randomUUID().toString());
        LoanEntity entity = loanMapper.toDbo(loan);

        loanTable.putItem(entity);
    }

    @Override
    public void update(Loan loan) {
        Key partitionKey = Key.builder().partitionValue(loan.getId()).build();

        LoanEntity entity = loanTable.getItem(partitionKey);

        if (null != entity) {
            save(loan);
        } else {
            throw new DboException("No Loan found for Id [%s]", loan.getId());
        }
    }

    @Override
    public void delete(String id) {
        Key partitionKey = Key.builder().partitionValue(id).build();

        loanTable.deleteItem(partitionKey);
    }
}
