package BasicApiTest;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
public class BasicApiTest01 {



    @Test
    public void GETUsersRequest() {

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.statusCode());
        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        System.out.println(response.statusLine());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void GETUsersRequest1() {

        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id[0]", equalTo(7));

    }

    @Test
    public void GETUsersRequest2() {

        given().get("https://reqres.in/api/users?page=2").then().
                statusCode(200).
                body("data.id[1]", equalTo(8)).
                body("data.first_name", hasItems("Michael","Lindsay")).
                log().all();

    }

    @Test
    public void PUTRequest() {

        JSONObject request = new JSONObject();
        request.put("name", "Robert");
        request.put("job", "Tech Consultant");

        System.out.println(request);
        System.out.println(request.toString());

        given().
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().statusCode(201 );

    }

    @Test
    public void PUTRequest2() {

        JSONObject request = new JSONObject();
        request.put("name", "Julie");
        request.put("job", "Business Analyst");

        System.out.println(request);
        System.out.println(request.toString());

        given().
                body(request.toJSONString()).
                when().
                put("https://reqres.in/api/users/2").
                then().statusCode(200);

    }
    @Test
    public void PATCHRequest() {

        JSONObject request = new JSONObject();
        request.put("name", "Robert");
        request.put("job", "Tech Consultant");

        System.out.println(request);
        System.out.println(request.toString());

        given().
                body(request.toJSONString()).
                when().
                patch("https://reqres.in/api/users").
                then().statusCode(404);

    }
    @Test
    public void DELETERequest() {

        JSONObject request = new JSONObject();
        given().
                body(request.toJSONString()).
                when().
                delete("https://reqres.in/api/users/2").
                then().statusCode(204).
                log().all();

    }

}

