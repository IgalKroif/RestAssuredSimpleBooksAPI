package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Random;

public class ReusableMethods {
    public static JsonPath rawToJson(String response)
    {
        JsonPath js = new JsonPath(response);
        return js;
    }
    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

}
