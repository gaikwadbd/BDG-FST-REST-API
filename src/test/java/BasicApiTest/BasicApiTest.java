package BasicApiTest;

import BasicApiTest.util.RestAssuredUtil;
import org.testng.annotations.Test;

public class BasicApiTest extends BaseTest {

    @Test
    public void T01_StatusCodeAndGetClientsTest() {
        res = RestAssuredUtil.getResponse("/gen/clients");
        testUtil.checkStatusIs200(res);
        jp = RestAssuredUtil.getJsonPath(res);
        System.out.println(testUtil.getClients(jp));
    }

    @Test
    public void T02_GetAndroidModelPackageOptions() {
        res = RestAssuredUtil.getResponse("/gen/clients/android");
        testUtil.checkStatusIs200(res);
        jp = RestAssuredUtil.getJsonPath(res);
        System.out.println("Opt: " + jp.get("modelPackage.opt"));
        System.out.println("Description: " + jp.get("modelPackage.description"));
        System.out.println("Type: " + jp.get("modelPackage.type"));
    }
}