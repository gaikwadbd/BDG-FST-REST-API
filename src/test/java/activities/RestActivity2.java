/*
Rest Assured : Activity2 : Petstore - POST, GET ,DELETE Request 
Author: Bharat
Created date:18/06/2023
FST Batch May 2023 
*/


package activities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
public class RestActivity2 {

        // Set base URL
        final static String baseURI = "https://petstore.swagger.io/v2/user";
        final static String filePath="C://Users//01979D744//Documents//FST2023//BDG-FST-REST-API//src//test//java//activities//";
        @Test(priority=1)
        public void POSTUserInfoFromFile() throws IOException {
            // Import JSON file
            FileInputStream inputJSON = new FileInputStream(filePath +"userinfo.json");
            // Read JSON file as String
            String reqBody = new String(inputJSON.readAllBytes());

            Response response =
                    given().contentType(ContentType.JSON) // Set headers
                            .body(reqBody) // Pass request body from file
                            .when().post(baseURI); // Send POST request

            inputJSON.close();

            // Assertion
            response.then().body("code", equalTo(200));
            response.then().body("message", equalTo("9901"));
            System.out.println("POST Request to add UserInfo is successfully sent");
            System.out.println(response.body().asPrettyString());
            System.out.println(response.statusLine());
        }

        @Test(priority=2)
        public void getUserInfo() {
            // Import JSON file to write to
            File outputJSON = new File(filePath +"userGETResponse.json");

            Response response =
                    given().contentType(ContentType.JSON) // Set headers
                            .pathParam("username", "justinc") // Pass request body from file
                            .when().get(baseURI + "/{username}"); // Send POST request

            // Get response body
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

            // Assertion
            response.then().body("id", equalTo(9901));
            response.then().body("username", equalTo("justinc"));
            response.then().body("firstName", equalTo("Justin"));
            response.then().body("lastName", equalTo("Case"));
            response.then().body("email", equalTo("justincase@mail.com"));
            response.then().body("password", equalTo("password123"));
            response.then().body("phone", equalTo("9812763450"));

            System.out.println("GET Request for UserInfo is successfully sent and response is written in userGETResponse.json file. ");
            System.out.println(response.body().asPrettyString());
            System.out.println(response.statusLine());
        }

        @Test(priority=3)
        public void deleteUser() throws IOException {
            Response response =
                    given().contentType(ContentType.JSON) // Set headers
                            .pathParam("username", "justinc") // Add path parameter
                            .when().delete(baseURI + "/{username}"); // Send POST request

            // Assertion
            response.then().body("code", equalTo(200));
            response.then().body("message", equalTo("justinc"));
            System.out.println("DELETE Request is successfully sent.");
            System.out.println(response.body().asPrettyString());
            System.out.println(response.statusLine());
        }
    }
