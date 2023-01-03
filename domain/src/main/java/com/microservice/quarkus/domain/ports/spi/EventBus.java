package com.microservice.quarkus.domain.ports.spi;

import java.util.Collection;

import com.microservice.quarkus.domain.shared.DomainEvent;

public interface EventBus {
    void publish(final Collection<DomainEvent> events);
}
