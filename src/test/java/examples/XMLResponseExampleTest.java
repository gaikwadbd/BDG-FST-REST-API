package examples;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class XMLResponseExampleTest {

    @Test(testName = "parsing XML response")
    public void xmlResponseValidation() {
        given().when().get("http://restapi.adequateshop.com/api/Traveler")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8")
                .header("Server", "Microsoft-IIS/10.0")
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].id", equalTo("11133"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].adderes", equalTo("USA")).log().all();

    }

    @Test(testName = "parsing XML response by using Object")
    public void xmlResponseObjectValidation() {
        Response res = given().when().get("http://restapi.adequateshop.com/api/Traveler");
        Assert.assertEquals(res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].adderes").toString(), "USA");

    }

    @Test(testName = "parsing XML response by using XML PATH")
    public void xmlPathResponseValidation() {
        Response res = given().when().get("http://restapi.adequateshop.com/api/Traveler");
        XmlPath xmlPathObj = new XmlPath(res.asString());
        System.out.println(xmlPathObj.getList("TravelerinformationResponse.travelers.Travelerinformation.id"));

        List<String> travelersList = xmlPathObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
        List<String> travelersName = xmlPathObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        for (String s : travelersList) {
            System.out.println(s);
        }
        for (String n : travelersName) {
            System.out.println(n);
        }
    }
}
