package dxc.microservice.quarkus.infrastructure.rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import javax.enterprise.inject.Produces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dxc.microservice.quarkus.application.ports.api.LoanAPIService;
import dxc.microservice.quarkus.domain.model.loan.Loan;
import dxc.microservice.quarkus.domain.model.loan.LoanFactory;
import dxc.microservice.quarkus.domain.model.loan.LoanId;
import dxc.microservice.quarkus.domain.ports.spi.EventBus;
import dxc.microservice.quarkus.domain.ports.spi.LoanRepository;
import dxc.micrservice.quarkus.infrastructure.rest.dto.LoanDTO;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class LoanResourceTest {

  @Mock
  @Produces
  LoanRepository loanRepository;

  @Mock
  @Produces
  EventBus eventBus;

  @Mock
  @Produces
  LoanFactory loanFactory;

  @InjectMock
  LoanAPIService loanService;

  @BeforeEach
  public void testPrepare() {
    LoanId loanId = LoanId.builder().id("4d3f3d2e-fb28-4315-a20c-a9975aa0cdc3").build();
    Loan dummyProp = Loan.builder().id(loanId)
                                   .annualInterestRate(1.15)
                                   .loanAmount(10000)
                                   .loanDate(new Date())
                                   .numberOfYears(1)
                                   .build();
    Mockito.when(loanService.getLoan("4d3f3d2e-fb28-4315-a20c-a9975aa0cdc3")).thenReturn(dummyProp);
    Mockito.when(loanService.getLoan("164e9d65-6804-4a48-a101-fadbd0e07149")).thenReturn(null);
  }

  @Test
  public void testPropByIdExists() {
    LoanDTO got = given()
        .pathParam("id", "4d3f3d2e-fb28-4315-a20c-a9975aa0cdc3")
        .when().get("/api/v1/loans/{id}")
        .then()
        .statusCode(200)
        .extract().as(LoanDTO.class);

    assertEquals(10000, got.getLoanAmount());
    assertEquals("4d3f3d2e-fb28-4315-a20c-a9975aa0cdc3", got.getId().toString());
    assertEquals(1, got.getNumberOfYears());
  }

  @Test
  public void testPropByIdNotExists() {
      given()
        .pathParam("id", "164e9d65-6804-4a48-a101-fadbd0e07149")
        .when().get("/api/v1/loans/{id}")
        .then()
        .statusCode(404);
  }
}
