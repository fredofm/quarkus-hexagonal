package dxc.microservices.quarkus.config.bootloader;

import javax.enterprise.inject.Produces;

import dxc.microservice.quarkus.domain.model.loan.LoanFactory;
import dxc.microservice.quarkus.domain.ports.spi.LoanRepository;

public class AppContext {
    @Produces
    public LoanFactory loanFactory(LoanRepository loanRepository) {
        return new LoanFactory(loanRepository);

    }
}
