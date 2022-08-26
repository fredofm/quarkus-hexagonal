package dxc.microservice.quarkus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dxc.microservice.quarkus.application.service.IProperService;
import dxc.microservice.quarkus.domain.model.Prop;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

@QuarkusTest
public class PropResourceTest {
  
  @InjectSpy  
  IProperService propService;

  @BeforeEach
  public void setup() {    
    Prop dummyProp = Prop.builder().name("zombie").running(false).id(2L).build();    
    Mockito.doReturn(dummyProp).when(propService).getProp(2L);    
  }

  @Test
  public void testGreetingEndpoint() {    
    Prop got = given()
        .pathParam("id", 2)
        .when().get("/api/v1/props/{id}")
        .then()
        .statusCode(200)
        .extract().as(Prop.class);

    assertEquals(false, got.isRunning());
    assertEquals(2L, got.getId());
    assertEquals("zombie", got.getName());
  }
}
