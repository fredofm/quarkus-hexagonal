package dxc.microservice.quarkus.infrastructure.db.dynamodb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import dxc.microservice.quarkus.domain.model.Prop;
import dxc.microservice.quarkus.infrastructure.db.dynamodb.dbo.PropEntity;

@Mapper(componentModel = "cdi")
public interface PropMapper {    
    PropEntity toDbo(Prop domain);

    Prop toDomain(PropEntity entity);

    void updateEntityFromDomain(Prop domain, @MappingTarget PropEntity entity);

    void updateDomainFromEntity(PropEntity entity, @MappingTarget Prop domain);
}