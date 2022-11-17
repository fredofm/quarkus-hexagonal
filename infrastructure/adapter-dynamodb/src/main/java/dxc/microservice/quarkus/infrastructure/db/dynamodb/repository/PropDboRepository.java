package dxc.microservice.quarkus.infrastructure.db.dynamodb.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import dxc.microservice.quarkus.application.repository.IPropRepository;
import dxc.microservice.quarkus.domain.model.Prop;
import dxc.microservice.quarkus.infrastructure.db.dynamodb.dbo.PropEntity;
import dxc.microservice.quarkus.infrastructure.db.dynamodb.exceptions.DboException;
import dxc.microservice.quarkus.infrastructure.db.dynamodb.mapper.PropMapper;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

@ApplicationScoped
@AllArgsConstructor
public class PropDboRepository implements IPropRepository {

    DynamoDbTable<PropEntity> propTable;

    PropMapper propMapper;    

    @Override
    public Prop findById(Long id) {
        Key partitionKey = Key.builder().partitionValue(id).build();
        return propMapper.toDomain(propTable.getItem(partitionKey));
    }

    @Override
    public List<Prop> getAll() {
        return propTable.scan().items().stream().map(p -> propMapper.toDomain(p)).collect(Collectors.toList());
    }

    @Override
    public void save(Prop prop) {
        PropEntity entity = propMapper.toDbo(prop);

        propTable.putItem(entity);
    }

    @Override
    public void update(Prop prop) {
        Key partitionKey = Key.builder().partitionValue(prop.getId()).build();

        PropEntity entity = propTable.getItem(partitionKey);

        if (null != entity) {
            save(prop);
        } else {
            throw new DboException("No Customer found for prop Id [%s]", prop.getId());
        }
    }
}
