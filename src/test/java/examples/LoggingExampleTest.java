package examples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoggingExampleTest {

    @Test(testName = "Logging request/response information")
    public void logAllExample() {
        given()
                .when().get("https://www.google.com/")
                .then()
                .log().all();           //entire response
//                .log().body();          //only body
//                .log().cookies();       //only cookies
//                .log().headers();       //only headers from response

    }

    @Test(testName = "Logging request/response by filters")
    public void usingOfLogging() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        given()
                .when().get("https://www.google.com/")
                .then();
    }
}
