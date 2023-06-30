package examples;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqResHTTPRequestsTest {
    int newUserID;

    @Test(testName = "Get list of users")       // Get list of users
    public void getListOfUsers() {
        given().when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data[0].id", equalTo(7))
                .log().all();
    }

    @Test(testName = "Get Simple user", priority = 1)       // Get single user by ID
    public void getUser() {
        given().
                when().get("https://reqres.in/api/users/2").
                then().statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.first_name", equalTo("Janet"))
                .log().all();
    }

    @Test(testName = "Create New User", priority = 2)       // Create new user
    public void createNewUser() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "morpheus");
        data.put("job", "trainer");
        newUserID = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
    }

    @Test(testName = "Update user by ID", dependsOnMethods = {"createNewUser"})     // Update user By ID
    public void updateUserById() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "morpheus");
        data.put("job", "dancer");
        given().contentType("application/json").body(data)
                .when().put("https://reqres.in/api/users/" + newUserID)
                .then().statusCode(200).body("job", equalTo(data.get("job"))).log().all();
    }

    @Test(testName = "Delete created user")     // Delete user By ID
    public void deleteUserById() {
        given().when().delete("https://reqres.in/api/users/" + newUserID).then().statusCode(204);
    }
}