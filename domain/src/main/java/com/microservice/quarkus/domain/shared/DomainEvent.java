package com.microservice.quarkus.domain.shared;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {    
    UUID eventId();
    
    Instant occurredOn();
}