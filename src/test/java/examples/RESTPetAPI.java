package examples;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.equalTo;


import groovy.json.JsonParser;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class RESTPetAPI {

    public static String baseURI = "https://petstore.swagger.io/v2/pet";

    @Test
    public void getPetDetails() {
        // Specify the base URL to the RESTful web service


        // Make a request to the server by specifying the method Type and
        // resource to send the request to.
        // Store the response received from the server for later use.
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().get(baseURI + "/findByStatus?status=sold"); // Run GET request

        // Now let us print the body of the message to see what response
        // we have received from the server
        String responseBody = response.getBody().asString();
        System.out.println("The Response of GetPetDetails are as follows: ");
        System.out.println("Response Body is =>  " + responseBody);

        // Assertions
        response.then().statusCode(200);
        response.then().body("[0].status", equalTo("sold"));
    }
    //Get Pet with query Params
    @Test
    public void getRequestWithQueryParameter() {
        Response response = given().header("content-Type", "application/json").queryParam("status", "alive").
                when().get(baseURI + "/findByStatus");

        //Response body
        System.out.println("The Response of getRequestWithQueryParameter are as follows: ");
        System.out.println(response.getBody().toString());
        System.out.println( response.getBody().asPrettyString());
        Reporter.log(response.getBody().asPrettyString());


        System.out.println(response.getHeaders().asList());
        System.out.println( response.getHeader("Date"));

        String petStatus=response.then().extract().path("[0].status");
        System.out.println(petStatus);
        System.out.println(response.statusLine());

    }
    //Post Request -Add new Pets
    @Test
    public void addNewPet()  {
        // Write the request body
        //String ROOT_URI = "https://petstore.swagger.io/v2/pet";
        String reqBody = "{\"id\": 77239,\"name\": \"Luther\",\"status\": \"alive\"}";

        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .body(reqBody).when().post(baseURI); // Send POST request

        // Print response of POST request
        String body = response.getBody().asPrettyString();
        System.out.println("The Response of AddNewPet are as follows: ");
        System.out.println(body);
        System.out.println(response.statusLine());
    }
    @Test
    public void addPetWithQueryParam()  {
        // Write the request body
        //String ROOT_URI = "https://petstore.swagger.io/v2/pet";
        String reqBody = "{\"id\": 77238,\"name\": \"Murphy\",\"status\": \"alive\"}";

        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .body(reqBody).when().post(baseURI); // Send POST request

        // Print response of POST request
        String body = response.getBody().asPrettyString();
        System.out.println("The Response of AddNewPet are as follows: ");
        System.out.println(body);
        System.out.println(response.statusLine());
    }
    @Test(dataProvider = "petIdProvider")
    public void getPetRequest(String id) {
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().get(baseURI +"/"+ id); // Send GET request

        // Print response
        System.out.println(response.asPrettyString());
        // Assertions
        response.then().body("status", Matchers.equalTo("alive"));
    }

    @DataProvider
    public Object[][] petIdProvider() {
        // Setting parameters to pass to test case
        Object[][] testData = new Object[][] { { "77238" }, { "77239" }};
        return testData;
    }
    //Put Request

    @Test
    public void updatePet() {
        // Write the request body
        String reqBody = "{\"id\": 77232,\"name\": \"Kelly\",\"status\": \"alive\"}";

        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .body(reqBody).when().put(baseURI); // Send PUT request

        // Print the response
        String resBody = response.getBody().asPrettyString();
        System.out.println(resBody);

        // Assert the updates
        response.then().body("name", equalTo("Kelly"));
    }
    //Delete Request
    @Test
    public void deletePet() {
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().delete(baseURI + "/77232"); // Send DELETE request

        // Assert DELETE operation
        /* However, this will only work the first time
         * the DELETE request is run, because
         * the pet with id 77232 has already been deleted.
         */
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("77232"));
        //response.then().body("message", equalTo("Pet not found"));
        System.out.println(response.getBody().asPrettyString());
        System.out.println(response.statusLine());

    }
    //JSON Schema Validation
    @Test
    public void validationResponseSchema() throws MalformedURLException {
        // Generate response
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().get(baseURI + "/77239"); // Send GET request

        // Print response
        System.out.println(response.asPrettyString());

        // Validate JSON Schema from URL
        URL swaggerSchema = new URL("https://petstore.swagger.io/v2/swagger.json");
        response.then().body(matchesJsonSchema(swaggerSchema));
        System.out.println(response.body().asPrettyString());
    }
    @Test
    public void addPetImage() {
        // Get file to upload
        File petImage = new File("C://Users//01979D744//Documents//FST2023//BDG-FST-REST-API//src//test//resources//pet_cat.jpg");
        Response response = given().multiPart(petImage) // Add image to upload
                .pathParam("petId", "77238") // Set petId parameter
                .when().post(baseURI+"/{petId}/uploadImage"); // Send POST request

        // Print response
        System.out.println(response.body().asPrettyString());

        // Assertion
        response.then().body("code", equalTo(200));
    }

    @Test (dataProvider = "petIdProvider")
    public void getPetInfo(String id) throws FileNotFoundException {
        // Set file path
        File resJSONFile = new File("C://Users//01979D744//Documents//FST2023//BDG-FST-REST-API//src//test//resources//PetInfo.json");
        Response response =
                given().contentType(ContentType.JSON) // Set headers
                        .when().get(baseURI + "/"+id); // Send GET request

        // Get response body
        String resBody = response.body().asPrettyString();

        try {
            // Create log file
            resJSONFile.createNewFile();
            // Write response body to external file
            FileWriter writer = new FileWriter(resJSONFile.getPath());
            FileInputStream inputJSON = new FileInputStream(resJSONFile);
            byte[] bytes = new byte[(int) resJSONFile.length()];
            writer.write(resBody);
            inputJSON.read(bytes);
            inputJSON.close();
            writer.close();
            System.out.println(resBody);
        } catch (IOException excp) {
            excp.printStackTrace();
        }
    }
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
                .when().post(baseURI); // Send POST request

        // Print response
        String body = response.getBody().asPrettyString();
        System.out.println(body);

        inputJSON.close();

        // Assertion
        response.then().body("id", Matchers.equalTo(77240));
        response.then().body("name", Matchers.equalTo("Puppy"));
    }
}
