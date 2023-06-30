package examples;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIAuthenticationTest {

        	@Test
        public void BasicAuth()
        {
            RequestSpecification requestSpec = RestAssured.given();

            //Specify URL  https://postman-echo.com/basic-auth
            requestSpec.baseUri("http://postman-echo.com");
            requestSpec.basePath("/basic-auth");

            //perform get request
            Response response = requestSpec.auth().preemptive().basic("postman", "password").get();

            //print status line
            System.out.println("Responsne status:" + response.statusLine());
            System.out.println("Response body:" + response.body().asString());

        }
        @Test
        public void DigestAuth()
        {
            RequestSpecification requestSpec = RestAssured.given();

            requestSpec.baseUri("http://httpbin.org/");
            requestSpec.basePath("digest-auth/undefined/abc/abc");

            Response resposne= requestSpec.auth().digest("abc", "abc").get();

            System.out.println("Responsne status:" + resposne.statusLine());
            System.out.println("Response body:" + resposne.body().asString());


        }

        @Test
        public void DigestAuthSpecifyAlgo()
        {
            RequestSpecification requestSpec = RestAssured.given();

            requestSpec.baseUri("http://httpbin.org/");
            requestSpec.basePath("digest-auth/undefined/abc/abc/SHA-256");

            Response resposne= requestSpec.auth().digest("abc", "abc").get();

            System.out.println("Responsne status:" + resposne.statusLine());
            System.out.println("Response body:" + resposne.body().asString());


        }

    }
