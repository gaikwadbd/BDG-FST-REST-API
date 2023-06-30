package examples;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {
    ResponseSpecification responseSpec = null;
    //since response code is repeteative so we use resposneSpecBuilder to avoid code redendency

    @BeforeClass
    public void createResposne()
    {
        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();

        responseBuilder.expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(3000L));



	    responseBuilder.expectStatusCode(200);
		responseBuilder.expectStatusLine("HTTP/1.1 200 OK");
		responseBuilder.expectContentType(ContentType.JSON);
		responseBuilder.expectResponseTime(Matchers.lessThan(3000L));

        responseSpec = responseBuilder.build();

    }

    @Test
    public void getBookingById()
    {

        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                .get()
                .then()

                .statusCode(200)
                .spec(responseSpec)
		        .statusLine("HTTP/1.1 200 OK")
	            .contentType(ContentType.JSON)
		        .time(Matchers.lessThan(3000L));

    }

    @Test
    public void getBookingByName()
    {

        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com/booking?firstname=sally")
                .when()
                .get()
                .then()

                .statusCode(200)
                .spec(responseSpec)
	            .statusLine("HTTP/1.1 200 OK")
		        .contentType(ContentType.JSON)
	            .time(Matchers.lessThan(3000L));
    }
}