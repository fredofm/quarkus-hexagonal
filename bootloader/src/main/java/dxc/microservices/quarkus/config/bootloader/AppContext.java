package dxc.microservices.quarkus.config.bootloader;

import javax.enterprise.inject.Produces;

import dxc.microservice.quarkus.application.ports.api.LoanAPIService;
import dxc.microservice.quarkus.application.service.LoanAPIServiceImpl;
import dxc.microservice.quarkus.domain.model.loan.LoanFactory;
import dxc.microservice.quarkus.domain.ports.spi.EventBus;
import dxc.microservice.quarkus.domain.ports.spi.LoanRepository;

public class AppContext {
    @Produces
    public LoanFactory loanFactory(LoanRepository loanRepository) {
        return new LoanFactory(loanRepository);
    }

    @Produces
    public LoanAPIService loanAPIService(LoanRepository loanRepository, EventBus eventBus, LoanFactory loanFactory) {        
        return new LoanAPIServiceImpl(loanRepository, loanFactory, eventBus);
    }
}
