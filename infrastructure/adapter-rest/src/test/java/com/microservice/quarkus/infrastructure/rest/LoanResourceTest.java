package com.microservice.quarkus.infrastructure.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.microservice.quarkus.application.ports.api.LoanAPIService;
import com.microservice.quarkus.domain.model.loan.Loan;
import com.microservice.quarkus.domain.model.loan.LoanId;
import com.micrservice.quarkus.infrastructure.rest.dto.LoanDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;

@QuarkusTest
public class LoanResourceTest {

  @InjectMock
  LoanAPIService loanAPIService;

  @BeforeEach
  public void beforeEach() {
    LoanId loanId = LoanId.builder().id("4d3f3d2e-fb28-4315-a20c-a9975aa0cdc3").build();
    Loan dummyProp = Loan.builder().id(loanId)
        .annualInterestRate(1.15)
        .loanAmount(10000)
        .loanDate(new Date())
        .numberOfYears(1)
        .build();

    Mockito.when(loanAPIService.getLoan("4d3f3d2e-fb28-4315-a20c-a9975aa0cdc3")).thenReturn(dummyProp);
    Mockito.when(loanAPIService.getLoan("164e9d65-6804-4a48-a101-fadbd0e07149")).thenReturn(null);
  }

  @Test
  public void testPropByIdExists() {
    LoanDTO got = RestAssured.given()
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
    RestAssured.given()
        .pathParam("id", "164e9d65-6804-4a48-a101-fadbd0e07149")
        .when().get("/api/v1/loans/{id}")
        .then()
        .statusCode(404);
  }
}
