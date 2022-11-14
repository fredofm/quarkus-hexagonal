package dxc.microservice.quarkus.infrastructure.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PropDto {
    private Long id;
    private boolean running;
    private String name;
}
