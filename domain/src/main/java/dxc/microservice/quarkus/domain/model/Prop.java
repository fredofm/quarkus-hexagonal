package dxc.microservice.quarkus.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Prop {
    private Long id;

    private boolean running;
    
    private String name;
}
