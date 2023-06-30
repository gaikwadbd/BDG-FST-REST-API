package examples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathParam {

    // Set Base URL with path parameter
    String ROOT_URI = "http://ip-api.com/json/{ipAddress}";

    @Test
    public void getIPInformation() {
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when()
                        .pathParam("ipAddress", "107.218.134.107") // Set path parameter
                        .get(ROOT_URI); // Send GET request

        // Print response
        System.out.println(response.getBody().asPrettyString());
    }

    @Test
    public void getIPDetails() {

        final String ROOT_URI = "http://ip-api.com/json";
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        // Add query parameter
                        .when().queryParam("fields", "query,country,city,timezone")
                        .get(ROOT_URI + "/125.219.5.94"); // Send GET request

        // Print response
        System.out.println(response.getBody().asPrettyString());
    }
}
