package examples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ChainingExampleDeleteTest {
    @Test(testName = "Delete user by userID")
    public void deleteUserById(ITestContext context) {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Response res = given()
                .header("Authorization", "Bearer a2cc")
                .contentType("application/json")
                .pathParam("userId", context.getSuite().getAttribute("user_id"))
                .when()
                .delete("https://gorest.co.in/public/v2/users/{userId}");
        Assert.assertEquals(res.statusCode(), 204);

    }
}
