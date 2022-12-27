package dxc.microservice.quarkus.infrastructure.events.kafka.incoming;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import dxc.microservice.quarkus.application.ports.commands.LoanStatusService;
import dxc.microservice.quarkus.domain.model.loan.LoanId;
import dxc.microservice.quarkus.infrastructure.events.kafka.LoanStateChangedArvo;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class LoanConsumer {
    LoanStatusService loanStatusService;

    @Incoming("loans-in")
    public void receive(LoanStateChangedArvo event) {
        log.info("Got a loan event: %d - %s", event);
        
        LoanId loanId = LoanId.builder().id(event.getLoanId()).build();

        loanStatusService.approveLoan(loanId);
    }
}
