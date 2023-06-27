package routes.get;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import routes.BookEndpoints;
import utilities.ContentTypeEnums;
import utilities.ReusableMethods;
import utilities.pojo.order.PojoBookOrder;

import static io.restassured.RestAssured.given;

public class PostUserEndPoints {
    public Response orderBook(PojoBookOrder payload) {

        String auth = "97f1b027a201e6c3c07044a612a23fb569ad1e2ab665bb4804e373746b292367";
        ContentTypeEnums contentType = ContentTypeEnums.JSON;
        return
        given()
                .body(payload)
                .header("Authorization", "Bearer " + auth)
                .contentType(contentType.getValue())
                .when()
                .post(BookEndpoints.orderBook);
        //.then().extract().response();
    }
}
