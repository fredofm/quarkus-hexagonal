package com.microservice.quarkus.infrastructure.rest.api;

import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import com.microservice.quarkus.application.ports.api.LoanAPIService;
import com.microservice.quarkus.domain.model.loan.Loan;
import com.microservice.quarkus.infrastructure.rest.mapper.LoanDTOMapper;
import com.micrservice.quarkus.infrastructure.rest.api.LoansAPI;
import com.micrservice.quarkus.infrastructure.rest.dto.LoanDTO;
import com.micrservice.quarkus.infrastructure.rest.dto.ResponseMessageDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class LoanResource implements LoansAPI{

    LoanAPIService loanService;

    LoanDTOMapper loanMapper;

    @Override
    public Response doGetAllLoans() {
        log.debug("doGetAllLoans()");
        
        Response response;

        var loans = loanService.getAllLoans();

        if (!loans.isEmpty()) {
            response = Response.ok(loans.stream().map(p -> loanMapper.toDto(p)).collect(Collectors.toList())).build();
        } else {
            response = Response.noContent().build();
        }
        
        return response;
    }

    @Override
    public Response doGetLoanById(UUID id) {        
        log.debug("doGetLoanById({})", id);

        Loan loan = loanService.getLoan(id.toString());
        Response response;

        if(null == loan) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            response = Response.ok(loanMapper.toDto(loan)).build();
        }

        return response;
    }

    @Override
    public Response doCreateLoan(LoanDTO loanDTO) {
        log.debug("doCreateLoan({})", loanDTO);

        loanService.create(loanMapper.toDomain(loanDTO));

        ResponseMessageDTO response = ResponseMessageDTO.builder().message("Loan created successfully").build();

        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @Override
    public Response doDeleteLoanById(UUID id) {
        log.debug("doDeleteLoanById({})", id);

        loanService.deleteLoan(id.toString());

        ResponseMessageDTO response = ResponseMessageDTO.builder().message("Loan deleted successfully").build();

        return Response.ok(response).build();
    }    

    /* 
     * @POST
        @APIResponse(responseCode = "201", description = "Prop Created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = PropDto.class)))
        @APIResponse(responseCode = "400", description = "Invalid Prop", content = @Content(mediaType = MediaType.APPLICATION_JSON))
        @APIResponse(responseCode = "400", description = "Prop already exists", content = @Content(mediaType = MediaType.APPLICATION_JSON))
        public Response post(@NotNull @Valid PropDto prop, @Context UriInfo uriInfo) {
                if (null != prop.getId() && null != propService.getLoan(prop.getId())) {
                        log.warn("Prop with id {} already exists. Skipping creation.", prop.getId());
                        return Response.status(Response.Status.BAD_REQUEST).build();
                }

                Loan propDomain = propMapper.toDomain(prop);
                propService.create(propDomain);
                PropDto propDto = propMapper.toDto(propDomain);
                URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(propDto.getId())).build();

                return Response.created(uri).entity(propDto).build();
     * 
     */
}
