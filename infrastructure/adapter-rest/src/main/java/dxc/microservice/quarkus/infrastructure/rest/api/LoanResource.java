package dxc.microservice.quarkus.infrastructure.rest.api;

import javax.ws.rs.core.Response;

import dxc.microservice.quarkus.application.service.IProperService;
import dxc.microservice.quarkus.infrastructure.rest.mapper.LoanMapper;
import dxc.micrservice.quarkus.infrastructure.rest.api.LoansAPI;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class LoanResource extends LoansAPI{

    IProperService propService;

    LoanMapper loanMapper;

    @Override
    protected Response doGetAllLoans() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Response doGetLoanById(Long id) {        
        // TODO Auto-generated method stub
        return null;
    }    
}
