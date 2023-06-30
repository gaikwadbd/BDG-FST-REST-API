package examples;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthenticationTokenTest {
    @SuppressWarnings("unchecked")
    @Test
    public void createUser()
    {

        RequestSpecification request=RestAssured.given();

        request.baseUri("https://gorest.co.in/");
        request.basePath("public/v2/users");

        JSONObject data=new JSONObject();

        data.put("name", "Bharat");
        data.put("gender", "Male");
        data.put("email", "test@12345.com");
        data.put("status", "active");


        String authToken="Bearer def0202310981a1dcf3c8b9f92f835087ebda87f47842fa3945747eeff19238d";
        Response response=request.header("Authorization",authToken)
                .contentType("application/json")
                .body(data.toJSONString()).post();
        JsonPath name=response.jsonPath();

        System.out.println(response.asString());
        System.out.println(response.statusCode());
        System.out.println(response.asPrettyString());
       }

}

