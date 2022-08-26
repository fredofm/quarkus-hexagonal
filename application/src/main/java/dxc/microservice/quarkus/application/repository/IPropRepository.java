package dxc.microservice.quarkus.application.repository;

import java.util.List;

import dxc.microservice.quarkus.domain.model.Prop;

public interface IPropRepository {
    
    List<Prop> getAll();

    Prop findById(Long id);

    void save(Prop prop);

    void update(Prop prop);
}
