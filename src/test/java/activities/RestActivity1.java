/*
Rest Assured : Activity1 : Petstore - POST, GET ,DELETE Request 
Author: Bharat
Created date:18/06/2023
FST Batch May 2023 
*/

package activities;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.equalTo;


import org.hamcrest.Matchers;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class RestActivity1 {
    // Set base URL
    final static String baseURI = "https://petstore.swagger.io/v2/pet";

    @Test(priority=1)
    public void POSTPetRequest() {
        // Create JSON request
        String reqBody = "{"
                + "\"id\": 77232,"
                + "\"name\": \"Riley\","
                + " \"status\": \"alive\""
                + "}";

        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .body(reqBody) // Add request body
                        .when().post(baseURI); // Send POST request

        // Assertion
        response.then().body("id", equalTo(77232));
        response.then().body("name", equalTo("Riley"));
        response.then().body("status", equalTo("alive"));
        System.out.println("POST Request with param Pet id:77232 name: Riley and status: alive is successfully sent");
        System.out.println(response.body().asPrettyString());
        System.out.println(response.statusLine());
    }

    @Test(priority=2)
    public void GETPetInfoRequest() {
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().pathParam("petId", "77232") // Set path parameter
                        .get(baseURI + "/{petId}"); // Send GET request

        // Assertion
        response.then().body("id", equalTo(77232));
        response.then().body("name", equalTo("Riley"));
        response.then().body("status", equalTo("alive"));
        System.out.println("GET Request for Pet id:77232 is successfully sent");
        System.out.println(response.body().asPrettyString());
        System.out.println(response.statusLine());
    }

    @Test(priority=3)
    public void DELETEPetRequest() {
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().pathParam("petId", "77232") // Set path parameter
                        .delete(baseURI + "/{petId}"); // Send DELETE request

        // Assertion Validation
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("77232"));
        System.out.println("DELETE Request for Pet id:77232 is successfully sent");
        System.out.println(response.body().asPrettyString());
        System.out.println(response.statusLine());
    }
}
