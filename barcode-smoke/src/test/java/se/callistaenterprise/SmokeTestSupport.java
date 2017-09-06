package se.callistaenterprise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.when;

@Slf4j
public abstract class SmokeTestSupport {

    @Before
    public void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

        log.info("baseURI :'{}', basePath :'{}', port: '{}' ", RestAssured.baseURI, RestAssured.basePath, RestAssured.port);

    }


//    /**
//     * Logs on to protected resources.
//     *
//     * @return the request spec with authentication provided.
//     */
//    public RequestSpecification login() {
//        return given().auth().basic(apiUser, apiPassword);
//    }
//
//    /**
//     * Logs on to protected resources.
//     *
//     * @return the request spec with authentication provided.
//     */
//    public RequestSpecification loginAdmin() {
//        return given().auth().basic(apiAdminUser, apiAdminPassword);
//    }


    /**
     * Normal REST JSON get with expected status.
     *
     * @return the response.
     */
    public ValidatableResponse get(final String relativePath, final int expectedStatus) {
//        login()
        return when()
                .get(relativePath)
                .then()
                .statusCode(expectedStatus)
                .assertThat()
                .contentType(ContentType.JSON);
    }

    /**
     * Normal REST JSON get with OK status.
     *
     * @return the response.
     */
    public ValidatableResponse get(final String relativePath) {
        return get(relativePath, HttpStatus.SC_OK);
    }
}
