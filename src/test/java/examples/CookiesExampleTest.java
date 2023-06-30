package examples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesExampleTest {
    @Test(testName = "Get cookies from Response object")
    public void getCookiesInfo() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//Get specific Cookie
        Response res = given().when().get("https://www.google.com/");
        String aecCookieValue = res.getCookie("AEC");
        String nidCookieValue = res.getCookie("NID");
        String onepCookieValue = res.getCookie("1P_JAR");
        System.out.println(aecCookieValue + "\n" + nidCookieValue + "\n" + onepCookieValue);
//Get all Cookies
        Map<String, String> cookiesMap = res.getCookies();
        for (String k : cookiesMap.keySet()) {
            String cookieValue = res.getCookie(k);
            System.out.println(k + " = " + cookieValue + "\n");
        }
    }
}
