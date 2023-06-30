package examples;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
public class BDDAPIPOSTTestDemo {

    @Test
    void POSTRequest()
    {
        JSONObject jsonData=new JSONObject();
        jsonData.put("name", "Sanket");
        jsonData.put("job", "Quality Assurance Engineer");


        baseURI="https://reqres.in/api/users";
        given()
                .header("Content-type","Application/Json")
                .contentType(ContentType.JSON)
                .body(jsonData.toJSONString())
                .when()
                .post()
                .then()
                .statusCode(201)
                .log()
                .all();
    }

    @Test
    void PUTRequest()
    {
        JSONObject jsonData=new JSONObject();
        jsonData.put("name", "Sanket Ruikar");
        jsonData.put("job", "Software Development Engineer");


        baseURI="https://reqres.in/api/users/420";
        given()
                .header("Content-type","Application/Json")
                .contentType(ContentType.JSON)
                .body(jsonData.toJSONString())
                .when()
                .put()
                .then()
                .statusCode(200)
                .log()
                .all();
    }
    @Test
    void PATCHRequest()
    {
        JSONObject jsonData=new JSONObject();
        jsonData.put("job", "Automation ENgineer");

        RestAssured.baseURI="https://reqres.in/api/users/420";
        RestAssured.given()
                .when()
                .patch()
                .then()
                .statusCode(200)
                .log()
                .all();
    }
    @Test
    void DELETERequest()
    {
        RestAssured.baseURI="https://reqres.in/api/users/420";
        RestAssured.given()
                .when()
                .delete()
                .then()
                .statusCode(204)
                .log()
                .all();

    }
}

