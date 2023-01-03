package com.microservices.quarkus.config.bootloader;

import javax.enterprise.inject.Produces;

import com.microservice.quarkus.application.ports.api.LoanAPIService;
import com.microservice.quarkus.application.service.LoanAPIServiceImpl;
import com.microservice.quarkus.domain.model.loan.LoanFactory;
import com.microservice.quarkus.domain.ports.spi.EventBus;
import com.microservice.quarkus.domain.ports.spi.LoanRepository;

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
