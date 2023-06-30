package examples;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SchemaValidationExampleTest {

//    Prepare schema.json or schema.xsd from response and keep it in src/test/resources

    @Test(testName = "JSON schema validation")
    public void jsonSchemaValidation() {
        given().
                when().get("https://reqres.in/api/users/3")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json"));
    }

    @Test(testName = "XML schema validation")
    public void xmlSchemaValidation() {
        given().when().get("http://restapi.adequateshop.com/api/Traveler")
                .then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xsd"));

    }
}
