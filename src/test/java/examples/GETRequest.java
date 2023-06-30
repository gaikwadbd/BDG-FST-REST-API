package examples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GETRequest {

    String baseURI = "https://petstore.swagger.io/v2/pet/";

    // Test case using a DataProvider
    @Test(dataProvider = "petIdProvider")
    public void getPetDetails(String id) {
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().get(baseURI + id); // Send GET request

        // Print response
        System.out.println(response.asPrettyString());
        // Assertions
        response.then().body("status", equalTo("alive"));
    }

    @DataProvider
    public Object[][] petIdProvider() {
        // Setting parameters to pass to test case
        Object[][] testData = new Object[][] { { "77238" }, { "77239" } };
        return testData;
    }
}
