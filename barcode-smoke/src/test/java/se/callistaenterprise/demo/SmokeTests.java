package se.callistaenterprise.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import se.callistaenterprise.SmokeTestSupport;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SmokeTests extends SmokeTestSupport  {

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



