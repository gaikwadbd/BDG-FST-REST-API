package examples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class SpecificationTest {
    final static String filePath="C://Users//01979D744//Documents//FST2023//BDG-FST-REST-API//src//test//java//activities//";
    // Declare request specification
    RequestSpecification requestSpec;
    // Declare response specification
    ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {
        // Create request specification
        requestSpec = new RequestSpecBuilder()
                // Set content type
                .setContentType(ContentType.JSON)
                // Set base URL
                .setBaseUri("https://petstore.swagger.io/v2/pet")
                // Build request specification
                .build();

        responseSpec = new ResponseSpecBuilder()
                // Check status code in response
                .expectStatusCode(200)
                .expectResponseTime(lessThanOrEqualTo(3000L))
                // Check response content type
                .expectContentType("application/json")
                // Check if response contains name property
                .expectBody("status", equalTo("alive"))
                // Build response specification
                .build();
    }

    @DataProvider
    public Object[][] petInfoDataProvider() {
        // Setting parameters to pass to test case
        Object[][] testData = new Object[][] {
                { 77241, "Matty", "alive" },
                { 77242, "Jocky", "alive" }
        };
        return testData;
    }

    @Test(priority=1)
    // Test case using a DataProvider
    public void addPets() {
        String reqBody = "{\"id\": 77241, \"name\": \"Matty\", \"status\": \"alive\"}";
        Response response = given().spec(requestSpec) // Use requestSpec
                .body(reqBody) // Send request body
                .when().post(); // Send POST request

        reqBody = "{\"id\": 77242, \"name\": \"Jocky\", \"status\": \"alive\"}";
        response = given().spec(requestSpec) // Use requestSpec
                .body(reqBody) // Send request body
                .when().post(); // Send POST request

        // Assertions
        response.then().spec(responseSpec); // Use responseSpec
        System.out.println("POST Request sent successfully.");
        System.out.println(response.body().asPrettyString());
        System.out.println(response.statusLine());
    }

    // Test case using a DataProvider
    @Test(dataProvider = "petInfoDataProvider", priority=2)
    public void getPetsDetails(int id, String name, String status) {
        File outputJSON = new File(filePath +"petGETResponse.json");
        Response response = given().spec(requestSpec) // Use requestSpec
                .pathParam("petId", id) // Add path parameter
                .when().get("/{petId}"); // Send GET request

        //Write Response in petGETResponse.json file
        String resBody = response.getBody().asPrettyString();
        try {
            // Create JSON file
            outputJSON.createNewFile();
            // Write response body to external file
            FileWriter writer = new FileWriter(outputJSON.getPath());
            writer.write(resBody);
            writer.close();
        } catch (IOException excp) {
            excp.printStackTrace();
        }
        // Print response
        System.out.println("GET Request sent successfully.");
        System.out.println(response.body().asPrettyString());
        System.out.println(response.statusLine());
        // Assertions
        response.then()
                .spec(responseSpec) // Use responseSpec
                .body("name", equalTo(name)); // Additional Assertion
    }

    // Test case using a DataProvider
    @Test(dataProvider = "petInfoDataProvider", priority=3)
    public void deletePets(int id, String name, String status) {
        Response response = given().spec(requestSpec) // Use requestSpec
                .pathParam("petId", id) // Add path parameter
                .when().delete("/{petId}"); // Send GET request

        // Assertions
        response.then().body("code", equalTo(200));
        System.out.println("DELETE Request sent successfully.");
        System.out.println(response.body().asPrettyString());
        System.out.println(response.statusLine());
    }

}
