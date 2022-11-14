package dxc.microservice.quarkus.infrastructure.db.hibernate.dbo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PropEntity {

    @Id @GeneratedValue private Long id;
    private boolean running;
    private String name;
}