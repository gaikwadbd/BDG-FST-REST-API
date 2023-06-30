package examples;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class XMLResponseValidation {

    static String BASEurl = "https://petstore.swagger.io/";
    @Test
    public void POSTRequestXMLData()
    {
              String XMLData="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
                + "<Pet>\r\n"
                + "	<id>77232</id>\r\n"
                + "	<Category>\r\n"
                + "		<id>0</id>\r\n"
                + "		<name>string</name>\r\n"
                + "	</Category>\r\n"
                + "	<name>Kelly</name>\r\n"
                + "	<photoUrls>\r\n"
                + "		<photoUrl>string</photoUrl>\r\n"
                + "	</photoUrls>\r\n"
                + "	<tags>\r\n"
                + "		<Tag>\r\n"
                + "			<id>0</id>\r\n"
                + "			<name>string</name>\r\n"
                + "		</Tag>\r\n"
                + "	</tags>\r\n"
                + "	<status>alive</status>\r\n"
                + "</Pet>";

        //Create RequestSpecification
        RequestSpecification request=RestAssured.given();

        //BaseURI && BAsePath

        request.baseUri(BASEurl);
        request.basePath("v2/pet");

         //add Headers && BODY -- XML Format
          request.header("accept","application/xml")
                .header("Content-Type","application/xml")
                .body(XMLData);


        //send POST request
        Response xmlresponse=request.post();
        System.out.println(xmlresponse.asPrettyString());
        System.out.println(xmlresponse.statusLine());
        Assert.assertEquals(xmlresponse.statusCode(), 200);
        xmlresponse.then().body("Pet.name",Matchers.equalTo("Kelly"));

    }

    @Test
    public void POSTRequestJSONData()
    {
        String JSONData="{\r\n"
                + "  \"id\": 77239,\r\n"
                + "  \"category\": {\r\n"
                + "    \"id\": 0,\r\n"
                + "    \"name\": \"string\"\r\n"
                + "  },\r\n"
                + "  \"name\": \"Murphy\",\r\n"
                + "  \"photoUrls\": [\r\n"
                + "    \"string\"\r\n"
                + "  ],\r\n"
                + "  \"tags\": [\r\n"
                + "    {\r\n"
                + "      \"id\": 0,\r\n"
                + "      \"name\": \"string\"\r\n"
                + "    }\r\n"
                + "  ],\r\n"
                + "  \"status\": \"alive\"\r\n"
                + "}";

        //Create RequestSpecification

        RequestSpecification request=RestAssured.given();

        //BaseURI && BAsePath

        request.baseUri(BASEurl);
        request.basePath("v2/pet");

          //add Headers && BODY -- JSON Format
          request.header("accept","application/json")
          .header("Content-Type","application/json")
          .body(JSONData);

          Response jsonresponse=request.post();
          System.out.println(jsonresponse.asPrettyString());
          System.out.println(jsonresponse.statusLine());
          Assert.assertEquals(jsonresponse.statusCode(), 200);
          jsonresponse.then().body("Pet.name",Matchers.equalTo("Murphy"));

        }
}

