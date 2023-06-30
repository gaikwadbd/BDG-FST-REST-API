package examples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POSTRequest {
    final static String ROOT_URI = "https://petstore.swagger.io/v2/pet";

    @Test
    public void addNewPetFromJSONFile() throws IOException {
        // Import JSON file
        File file = new File("C://Users//01979D744//Documents//FST2023//BDG-FST-REST-API//src//test//resources//inputPet.json");
        FileInputStream inputJSON = new FileInputStream(file);
        // Get all bytes from JSON file
        byte[] bytes = new byte[(int) file.length()];
        inputJSON.read(bytes);
        // Read JSON file as String
        String reqBody = new String(bytes, "UTF-8");

        System.out.println(reqBody);

        Response response = given()
                .contentType(ContentType.JSON) // Set headers
                .body(reqBody) // Pass request body from file
                .when().post(ROOT_URI); // Send POST request

        // Print response
        String body = response.getBody().asPrettyString();
        System.out.println(body);

        inputJSON.close();

        // Assertion
        response.then().body("id", equalTo(77240));
        response.then().body("name", equalTo("Puppy"));
    }
}
