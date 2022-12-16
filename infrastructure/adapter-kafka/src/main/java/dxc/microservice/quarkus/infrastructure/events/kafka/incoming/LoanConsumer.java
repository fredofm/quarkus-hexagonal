package dxc.microservice.quarkus.infrastructure.events.kafka.incoming;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.smallrye.reactive.messaging.kafka.Record;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class LoanConsumer {
    @Incoming("prices")
    public void receive(Record<Integer, String> record) {
        log.info("Got a movie: %d - %s", record.key(), record.value());
    }
}
