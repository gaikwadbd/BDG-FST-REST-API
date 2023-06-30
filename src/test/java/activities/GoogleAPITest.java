package activities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class GoogleAPITest {

    public static void main(String[] args){

        String key = "qaclick123";
        // validate addPlace API is working as expected
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //addPlace - POST
        String responseAddPlace = given().queryParam("key",key)
                .header("Content-Type", "application/json")
                .body(PayLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server","Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();

        System.out.println(responseAddPlace);

        // to parse json
        JsonPath json = MyMethod.convertRawToJson(responseAddPlace);

        String placeId = json.getString("place_id");
        System.out.println(placeId);

        //updatePlace - PUT
        String responseUpdatePlace = given().queryParam("key",key)
                .header("Content-Type", "application/json")
                .body(PayLoad.updatePlace(key,placeId))
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"))
                .extract().response().asString();

        System.out.println(responseUpdatePlace);

        //getPlace - GET
        String responseGetPlace = given().queryParam("key",key)
                .queryParam("place_id",placeId)
                .header("Content-Type", "application/json")
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println(responseGetPlace);

        //assertEquals TestNG
        JsonPath responseGetPlaceJsn = MyMethod.convertRawToJson(responseGetPlace);
        Assert.assertEquals(responseGetPlaceJsn.getString("address"), "70 winter walk, USA");
    }
}

