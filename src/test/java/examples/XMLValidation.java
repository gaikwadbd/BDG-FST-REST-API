package examples;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class XMLValidation {

        @Test
        public void getData()
        {
            //RequestSpecification
            RequestSpecification request=RestAssured.given();

            //Add baseUri && basePath
            request.baseUri("http://restapi.adequateshop.com/");
            request.basePath("api/Traveler?page=1");

            //Add headers
            request.header("content-type","application/xml")
                    .queryParam("page",1);

            //Send get Request
            Response resposne=request.get();
            System.out.println(resposne.asPrettyString());

            //Approach 1- validate USing Matchers
            resposne
                    .then()
                    .body("TravelerinformationResponse.travelers.Travelerinformation[0].id",Matchers.equalTo("11133"));

            //Appraoch 2- XMLPAth use karaycha

            XmlPath xml=new XmlPath(resposne.asString());
            //.get krun specific index chi value kadhun ghaychi
            String name=xml.get("TravelerinformationResponse.travelers.Travelerinformation[0].name");
            Assert.assertEquals(name,"Developer","Not an dev");

            //Verify total travelers to be 10
            //1)Get krun list kadhun ghaychi ani mg tya list chi size 10 ahe ka bghaycha
            List list=xml.getList("TravelerinformationResponse.travelers.Travelerinformation");
            int numberOfTravellers=list.size();
            Assert.assertEquals(numberOfTravellers, 10,"List is not complete");

            //verify for name vano in travellers list

            List<String> list2=xml.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
            boolean found = false;
            for(String list3:list2)
            {
                if(list3.equals("vano"))
                {
                    found=true;
                    break;
                }
            }
            Assert.assertEquals(found, true);

        }

    }

