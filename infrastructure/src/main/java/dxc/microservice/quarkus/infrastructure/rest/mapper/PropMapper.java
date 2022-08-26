package dxc.microservice.quarkus.infrastructure.rest.mapper;

import org.mapstruct.Mapper;

import dxc.microservice.quarkus.domain.model.Prop;
import dxc.microservice.quarkus.infrastructure.rest.dto.PropDto;

@Mapper(componentModel = "cdi") 
public interface PropMapper {
    
    public PropDto toDto(Prop prop);

    public Prop toDomain(PropDto propDto);
}
