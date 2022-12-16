package dxc.microservice.quarkus.domain.ports.spi;

import dxc.microservice.quarkus.domain.shared.DomainEvent;
import dxc.microservice.quarkus.domain.shared.RootAggregate;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent<RootAggregate>> events);
}
