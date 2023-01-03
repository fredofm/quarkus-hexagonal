package com.microservice.quarkus.domain.shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RootAggregate {
    private ArrayList<DomainEvent> domainEvents = new ArrayList<>();

    public void registerEvent(@NonNull DomainEvent event) {
        log.debug("registerEvent({})", event);
        domainEvents.add(event);
    }

    /**
     * Called by the persistence framework to clear all registered domain events once they have been published.
     */
    public void clearDomainEvents() {
        log.debug("clearDomainEvents()");
        domainEvents.clear();
    }

    /**
     * Returns all domain events that have been registered for publication. Intended to be used by the persistence
     * framework only.
     */    
    public Collection<DomainEvent> domainEvents() {
        log.debug("domainEvents()");
        return Collections.unmodifiableList(domainEvents);
    }
}