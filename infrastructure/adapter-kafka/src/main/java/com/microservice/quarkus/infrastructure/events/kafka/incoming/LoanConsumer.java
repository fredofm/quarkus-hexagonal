package com.microservice.quarkus.infrastructure.events.kafka.incoming;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.microservice.quarkus.application.ports.commands.LoanStatusService;
import com.microservice.quarkus.domain.model.loan.LoanId;
import com.microservice.quarkus.infrastructure.events.kafka.LoanStateChangedArvo;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class LoanConsumer {
    LoanStatusService loanStatusService;

    @Incoming("loans-in")
    public void receive(LoanStateChangedArvo event) {
        log.info("Got a loan event: {}", event);
        
        LoanId loanId = LoanId.builder().id(event.getLoanId()).build();

        try {
            loanStatusService.approveLoan(loanId);
        } catch (RuntimeException e) {
            // TODO error logic
        }
    }
}
