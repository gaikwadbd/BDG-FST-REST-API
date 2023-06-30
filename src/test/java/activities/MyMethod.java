package activities;
import io.restassured.path.json.JsonPath;
public class MyMethod {
        public static JsonPath convertRawToJson(String response){
            JsonPath json = new JsonPath(response);
            return json;
        }
    }

