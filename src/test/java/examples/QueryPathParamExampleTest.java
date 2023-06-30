package examples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class QueryPathParamExampleTest {

    @Test(testName = "Specify Query and Path parameters in request")
    public void queryAndPath() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());       //Log all
        given()
                .pathParam("myPath0", "api")
                .pathParam("myPath1", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
                .when().get("https://reqres.in/{myPath0}/{myPath1}")
                //              https://reqres.in/api/users?page=2&id=5
                .then().statusCode(200);
    }
}