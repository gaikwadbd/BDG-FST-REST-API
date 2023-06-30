package examples;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class O2AuthenticationTest {
        static String accessToken;
        @Test
        public void PostAuthToken()
        {
            String clientId = "Af6ZaIMAoqOuIxoWdFXu0-DagBIO-16R84vyhxBkAIGynL8IIuTrue3qP3wAOmAZ52qSV3I240SsXkoP";
            String clientSecret = "EBWh5kucLCxYHILbnQiAHeG6fpz4cFZ1YH4mNXjlGo92BdZkSTr0iLZW_nrSIvKYylpaQ26oVtaJAQd6";

            RequestSpecification requestSpec = RestAssured.given();

            requestSpec.baseUri("https://api-m.sandbox.paypal.com/");
            requestSpec.basePath("/v1/oauth2/token/");


            Response response=requestSpec.auth().basic(clientId, clientSecret).param("grant_type", "client_credentials").post();
            System.out.println(response.prettyPrint());


            //get access token from response body.

            accessToken= response.getBody().path("access_token");
        }

        @Test
        public void getInvoice()
        {
            //page=3&page_size=4&total_count_required=true
            Response requestSpec = RestAssured.given()
                    .auth()
                    .oauth2(accessToken)
                    .queryParam("page","3")
                    .queryParam("size", "4")
                    .queryParam("total_count_required",true)
                    .get("https://api-m.sandbox.paypal.com/v1/invoicing/invoices/");

            requestSpec.prettyPrint();
            //print status code & status line
            System.out.println("Response code:" + requestSpec.statusCode() );
            System.out.println("status line:" + requestSpec.statusLine() );

            //validate repsonse code
            Assert.assertEquals(requestSpec.statusCode(), 200,"Verify Status Code is correct");
        }
    }