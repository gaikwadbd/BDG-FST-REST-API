package examples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class SchemaValidation {
    String baseURI = "https://petstore.swagger.io/v2/pet";
    //JSON Schema Validation
    @Test
    public void getPetInfo() throws MalformedURLException {
        // Generate response
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().get(baseURI + "/77239"); // Send GET request

        // Print response
        System.out.println(response.asPrettyString());
        String url ="https://petstore.swagger.io/v2/swagger.json";
        // Validate JSON Schema from URL
        URL swaggerSchema = new URL(url);
        response.then().body(matchesJsonSchema(swaggerSchema));
        System.out.println(response.body().asPrettyString());
    }
}
