package com.microservice.quarkus.infrastructure.events.kafka.outgoing;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.microservice.quarkus.domain.events.LoanStateChanged;
import com.microservice.quarkus.domain.ports.spi.EventBus;
import com.microservice.quarkus.domain.shared.DomainEvent;
import com.microservice.quarkus.infrastructure.events.kafka.LoanStateChangedArvo;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class LoanProducer implements EventBus {
    
    @Channel("loans-out")
    Emitter<LoanStateChangedArvo> emitter;

    @Override
    public void publish(Collection<DomainEvent> events) {
        log.debug("publish({})", events);
        events.forEach(event -> {
            LoanStateChanged loanEvent = ((LoanStateChanged)event);
            emitter.send(LoanStateChangedArvo.newBuilder()
                    .setEventId(loanEvent.getEventId().toString())
                    .setFromState(loanEvent.getFromState().toString())
                    .setOccurredOn(loanEvent.occurredOn().toString())
                    .setState(loanEvent.getState().toString())
                    .setLoanId(loanEvent.getLoanId().getId())
                    .build());
        });
    }
}
