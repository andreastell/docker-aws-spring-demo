package se.callistaenterprise.demo;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import se.callistaenterprise.SmokeTestSupport;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Slf4j
public class SmokeTests extends SmokeTestSupport {

    private static final String CONTEXT_PATH = "/breakfast";

    @Test
    public void canAccessGoogle() {
        given().when().get("http://www.google.com:80").then().statusCode(200);
    }

    @Test
    public void appIsUp() {
        get(CONTEXT_PATH + "/admin/health");
    }

    @Test
    public void barcodesWork() {
        given().when().get(CONTEXT_PATH + "/barcodes").then().statusCode(200);
    }

    @Test
    public void greetingsWork() {
        get(CONTEXT_PATH + "/greetings?name=test")
                .body("message", is("Howdy test !"));
    }

}



