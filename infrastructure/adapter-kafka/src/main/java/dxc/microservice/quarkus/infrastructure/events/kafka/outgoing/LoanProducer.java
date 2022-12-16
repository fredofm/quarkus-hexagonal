package dxc.microservice.quarkus.infrastructure.events.kafka.outgoing;

import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import dxc.microservice.quarkus.domain.model.loan.Loan;
import io.smallrye.reactive.messaging.kafka.Record;

public class LoanProducer {
    @Inject @Channel("movies-out")
    Emitter<Record<String, Long>> emitter;

    public void sendMovieToKafka(Loan loan) {
        emitter.send(Record.of(loan.getId().getId(), loan.getLoanAmount()));
    }
}
