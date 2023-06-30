package examples;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.ValidatableResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
public class ValidateHTTPSResponse {

    String ExpectedStatusCodeLine="HTTP/1.1 200 OK";
    int ExpectedStatusCode=200;
    @Test
    void validateHTTPSResponseIncorrectStatus()
    {

        baseURI="https://reqres.in/api/users/2tt";

        RequestSpecification request=given();
        Response response=request.get();

        String actutalStatusLine=response.getStatusLine();
        System.out.println("Status code: " + actutalStatusLine);

        Assert.assertEquals(actutalStatusLine, ExpectedStatusCodeLine,"Incorrect Status Line received");
    }


        @Test
        void validateHTTPSResponseCorrectStatus()
        {

            baseURI="https://reqres.in/api/users/2";

            RequestSpecification request=given();


            Response response=request.get();
            int actutalStatusCode=response.statusCode();
            System.out.println("Status code: "+ actutalStatusCode); ;

            Assert.assertEquals(actutalStatusCode, ExpectedStatusCode,"Right code received");
        }
    @Test
    void validateStatusCode()
    {

        baseURI="https://reqres.in/api/users/2";

        RequestSpecification request=given();


        Response response=request.get();

        ValidatableResponse validate=response.then();

        validate.statusCode(ExpectedStatusCode);


    }

    @Test
    public void validateResponseBodyContent()
    {
        RequestSpecification request =RestAssured.given();
        request.baseUri("https://reqres.in");
        request.basePath("/api/users?page/2");

        Response response=request.get();

        ResponseBody responseBody=response.getBody();

        String body=responseBody.asString();

        System.out.println(body);

        //Check if Janet is present in response


        Assert.assertEquals(body.contains("Janet"), true,"Not present");

        //Traversing through to NODES
        JsonPath js =responseBody.jsonPath();
        System.out.println(js.get("data[1].id").toString());
        System.out.println(js.get("data[1].email").toString());
        System.out.println(js.get("data[1].first_name").toString());
        System.out.println(js.get("data[1].last_name").toString());
        System.out.println(js.get("data[1].avatar").toString());

        String f_name=js.get("data[1].first_name");

        Assert.assertEquals(f_name, "Janet","Not present");
    }
}

