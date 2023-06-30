package examples;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MockAPITestDemo {
    //Convert JSON Response To POJO Class Object//
    @Test
    public void demo()
    {

        //RequestSpecification
        RequestSpecification request=RestAssured.given();
        //add base uri and headers
        request.baseUri("https://run.mocky.io/v3/42df5ca8-0533-40a2-bdf0-b531c6bde766");
        //send request
        Response response=request.get();
        //print response
        response.prettyPrint();
        //assert statcusCode
        Assert.assertEquals(response.statusCode(),200, "Wrong staus Code");


    }
    @Test
    public void demo2()
    {

        //RequestSpecification
        RequestSpecification request=RestAssured.given();
        //add base uri and headers
        request.baseUri("https://run.mocky.io/v3/42df5ca8-0533-40a2-bdf0-b531c6bde766");
        //send request and get JSON response as Java class Object
        MockAPITest response=request.get().as(MockAPITest.class);
        //print response
        System.out.println("MockAPITest Java Object Response");
        System.out.println("============================");
        System.out.println(response.getName());
        System.out.println(response.getStatus());
        System.out.println(response.getGender());



    }

}