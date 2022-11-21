package dxc.microservice.quarkus.infrastructure.rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.enterprise.inject.Produces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dxc.microservice.quarkus.application.repository.IPropRepository;
import dxc.microservice.quarkus.application.service.IProperService;
import dxc.microservice.quarkus.domain.model.Prop;
import dxc.microservice.quarkus.infrastructure.rest.dto.PropDto;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class PropResourceTest {

  @Mock
  @Produces
  IPropRepository propRepository;

  @InjectMock
  IProperService propService;

  @BeforeEach
  public void testPrepare() {
    Prop dummyProp = Prop.builder().name("zombie").running(false).id(2L).build();
    Mockito.when(propService.getProp(2L)).thenReturn(dummyProp);
  }

  @Test
  public void testPropByIdExists() {
    PropDto got = given()
        .pathParam("id", 2)
        .when().get("/api/v1/props/{id}")
        .then()
        .statusCode(200)
        .extract().as(PropDto.class);

    assertEquals(false, got.isRunning());
    assertEquals(2L, got.getId());
    assertEquals("zombie", got.getName());
  }

  @Test
  public void testPropByIdNotExists() {
      given()
        .pathParam("id", 1)
        .when().get("/api/v1/props/{id}")
        .then()
        .statusCode(204);
  }
}
