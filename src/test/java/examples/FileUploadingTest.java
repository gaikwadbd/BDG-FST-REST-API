package examples;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FileUploadingTest {

    @Test(testName = "Uploading file by http request")
    public void uploadingFile() {
        File myfile = new File("src/test/resources/postBody.json");
        given()
                .multiPart("file", myfile)
                .contentType("multipart/form-data")                     //when uploading files, use this content type
                .header("Authorization", "token")
                .param("paramName", "paramValue")
                .when()
                .post("/upload-endpoint")
                .then()
                .statusCode(200);
    }
}
