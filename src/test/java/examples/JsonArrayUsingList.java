package examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonArrayUsingList {
    @Test
    public void demo()
    {
        Map<String,Object> user1=new HashMap<String,Object>();
        user1.put("firstName","Sachin");
        user1.put("lastName","Sharma");
        user1.put("age","40");
        user1.put("salary",2000000000.00);

        Map<String,Object> user2=new HashMap<String,Object>();
        user2.put("firstName","Pallavi");
        user2.put("lastName","Joshi");
        user2.put("age","36");
        user2.put("salary",500000000.00);

        Map<String,Object> user3=new HashMap<String,Object>();
        user3.put("firstName","Aditi");
        user3.put("lastName","Jog");
        user3.put("age","42");
        user3.put("salary",500000000.00);

        List<Map<String,Object>> jsPayload=new ArrayList<Map<String,Object>>();
        jsPayload.add(user1);
        jsPayload.add(user2);
        jsPayload.add(user3);

        //create Request Specification
        RequestSpecification reqSpec = RestAssured.given();

        //specify URL
        reqSpec.baseUri("https://reqres.in/api/users");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.body(jsPayload);

        //perform post request
        Response response = reqSpec.post();

        response.prettyPrint();
        System.out.println(response.statusLine());
       //Validate the status code
        Assert.assertEquals(response.statusCode(), 201,"Verify that status code is correct.");
    }
}