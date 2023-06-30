package BasicApiTest;

import BasicApiTest.util.RestAssuredUtil;
import BasicApiTest.util.TestUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public Response res = null; //Response
    public JsonPath jp  = null; //JsonPath

    //Instantiate a Helper Test Methods (testUtils) Object
    TestUtil testUtil = new TestUtil();

    @BeforeClass
    public void setup() {
        //Test Setup
        RestAssuredUtil.setBaseURI(); //Setup Base URI
        RestAssuredUtil.setBasePath("api"); //Setup Base Path
        RestAssuredUtil.setContentType(ContentType.JSON); //Setup Content Type
    }

    @AfterClass
    public void afterTest() {
        //Reset Values
        RestAssuredUtil.resetBaseURI();
        RestAssuredUtil.resetBasePath();
    }
}