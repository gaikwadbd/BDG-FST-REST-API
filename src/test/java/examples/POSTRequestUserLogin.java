package examples;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POSTRequestUserLogin {

    @Test
    public void CreateAuthenticationToken()
    {
        //RequestSpecification create

        RequestSpecification request =RestAssured.given();

        //add basePath and baseURI

        request.baseUri("https://restful-booker.herokuapp.com/");
        request.basePath("/auth");

        //Create JSOnOBject for input data by using Map in java

        Map<String, String> authToken = new HashMap<String, String>();
        authToken.put("username", "admin");
        authToken.put("password", "password123");

        //add header
        request.contentType(ContentType.JSON)
                .body(authToken);

        //send-request
        Response response=request.post();

        System.out.println(response.asPrettyString());
        System.out.println(response.statusLine());
        //verify status code
        Assert.assertEquals(response.statusCode(), 200,"Verify status code is correct.");
    }
}