package examples;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JSONArrayRequestBodyTest {

    @SuppressWarnings("unchecked")
    @Test
    public void POSTRequest()
    {
        //create 3 users Data JOSN array

        JSONObject user1 = new JSONObject();
        user1.put("firstName","Aniruddha");
        user1.put("lastName","Deshpande");
        user1.put("age",44);
        user1.put("salary",5500000000.00);

        JSONObject user2 = new JSONObject();
        user2.put("firstName","Reshma");
        user2.put("lastName","Pawar");
        user2.put("age",38);
        user2.put("salary",160000000.00);

        JSONObject user3 = new JSONObject();
        user3.put("firstName","Sumant");
        user3.put("lastName","Desai");
        user3.put("age",35);
        user3.put("salary",1000000000.00);


        //by using 3 JSONObject create 1 array of JSON

        JSONArray jsArray=new JSONArray();
        jsArray.add(user1);
        jsArray.add(user2);
        jsArray.add(user3);

        //create Request Specification
        RequestSpecification reqSpec = RestAssured.given();

        //specify URL
        reqSpec.baseUri("https://reqres.in/api/users");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.body(jsArray);

        //perform post request
        Response response = reqSpec.post();

        response.prettyPrint();

        //Validate the status code
        Assert.assertEquals(response.statusCode(), 201,"Verify the status code is correct");

    }
}