package examples;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTest {

    @Test(testName = "Basic authentication test")
    public void basicAuth() {
        given().auth().basic("postman", "password")
                .when().get("https://postman-echo.com/basic-auth").then().statusCode(200).log().all();
    }

    @Test(testName = "Digest authentication test")
    public void digestAuth() {
        given().auth().basic("postman", "password")
                .when().get("https://postman-echo.com/basic-auth").then().statusCode(200).log().all();
    }

    @Test(testName = "Bearer authentication test")
    public void bearerTokenAuth() {
        given().headers("Authorization", "Bearer " + "a2c59862a96aeb492fb26d8d243f2fd08b236dee2a909e67c2f1ce1d6edce5fc")
                .when().post("https://gorest.co.in/public/v2/users")
                .then().statusCode(200);
    }

    @Test(testName = "Oauth 1.0 token authorization")
    public void oauth1Auth() {
        given().auth().oauth("consumerKey", "consumerSecret", "access token", "tokenSecret")
                .when().get("URL");
    }

    @Test(testName = "Oauth 2.0 token authorization")
    public void oauth2Auth() {
        given().auth().oauth2("oauth token")
                .when().get("URL")
                .then().statusCode(200);
    }

    @Test(testName = "API key Authorization")
    public void apiKeyAuth() {
        given().queryParam("myKey", "apiKey")       //appid its API KEY
                .when().get("URL")
                .then();
    }
}

