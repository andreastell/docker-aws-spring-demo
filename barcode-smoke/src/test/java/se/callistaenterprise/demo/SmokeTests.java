package se.callistaenterprise.demo;


import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import se.callistaenterprise.SmokeTestSupport;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Slf4j
public class SmokeTests extends SmokeTestSupport  {

	@Test
	public void canAccessGoogle() {
		given().when().get("http://www.google.com:80").then().statusCode(200);
	}

	@Test
	public void appIsUp() {
		get("/admin/health");
	}

	@Test
	public void greetingsWork() {
		get("/greetings?name=test")
				.body("message", is("Howdy test !"));
	}

}



