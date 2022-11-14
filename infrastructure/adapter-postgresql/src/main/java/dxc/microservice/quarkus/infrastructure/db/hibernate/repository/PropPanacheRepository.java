package dxc.microservice.quarkus.infrastructure.db.hibernate.repository;

import javax.enterprise.context.ApplicationScoped;

import dxc.microservice.quarkus.infrastructure.db.hibernate.dbo.PropEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PropPanacheRepository implements PanacheRepository<PropEntity> {

}