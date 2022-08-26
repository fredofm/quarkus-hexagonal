package dxc.microservice.quarkus.infrastructure.db.hibernate.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import dxc.microservice.quarkus.application.repository.IPropRepository;
import dxc.microservice.quarkus.domain.model.Prop;
import dxc.microservice.quarkus.infrastructure.db.hibernate.dbo.PropEntity;
import dxc.microservice.quarkus.infrastructure.db.hibernate.exceptions.DboException;
import dxc.microservice.quarkus.infrastructure.db.hibernate.mapper.PropMapper;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class PropDboRepository implements IPropRepository {

    PropPanacheRepository repository;

    PropMapper propMappper;

    @Override
    public Prop findById(Long id) {
        return propMappper.toDomain(repository.findById(id));
    }

    @Override
    public List<Prop> getAll() {
        return repository.findAll().stream().map(p -> propMappper.toDomain(p)).collect(Collectors.toList());
    }

    @Override
    public void save(Prop prop) {
        PropEntity entity = propMappper.toDbo(prop);
        repository.persistAndFlush(entity);
        propMappper.updateDomainFromEntity(entity, prop);
    }

    @Override
    public void update(Prop prop) {        
        PropEntity entity = repository.findByIdOptional(prop.getId())
                .orElseThrow(() -> new DboException("No Customer found for prop Id [%s]", prop.getId()));
        propMappper.updateEntityFromDomain(prop, entity);
        repository.persist(entity);
        propMappper.updateDomainFromEntity(entity, prop);        
    }
}
