package dxc.microservice.quarkus.domain.ports.spi;

import java.util.Collection;

import dxc.microservice.quarkus.domain.shared.DomainEvent;

public interface EventBus {
    void publish(final Collection<DomainEvent> events);
}
