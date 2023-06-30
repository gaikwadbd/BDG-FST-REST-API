package examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POSTRequestUsingMap {

    @Test
    public void demo()
    {
        //Create Map object
        Map<String,Object> userData=new HashMap<String,Object>();
        userData.put("firstName","Anil");
        userData.put("lastName","Shinde");
        userData.put("age","40");
        userData.put("salary",10000000.00);
        userData.put("IsMarried", true);

        //Create ArrayList
        ArrayList<String> hobbies=new ArrayList<String>();
        hobbies.add("Trekkings");
        hobbies.add("Travelling");
        hobbies.add("Photography");
        userData.put("Hobbies", hobbies);
        //Create Map
        Map<String,Object> skills=new HashMap<String,Object>();
        skills.put("Programming language","Python");
        skills.put("WebAutomation","Cypress");
        skills.put("Performance Testing","Jmeter");
        userData.put("Skills", skills);

        //RequestSpecification create

        RequestSpecification request =RestAssured.given();

        //add basePath and baseURI

        request.baseUri("https://reqres.in/");
        request.basePath("api/users");

        //add header
        request.contentType(ContentType.JSON)
                .body(userData);

        //send-request
        Response response=request.post();
        //Display the Response
        System.out.println(response.asPrettyString());
        System.out.println(response.statusLine());
        //Verify the status code is correct
        Assert.assertEquals(response.statusCode(), 201,"Verify status code is correct.");
    }

}