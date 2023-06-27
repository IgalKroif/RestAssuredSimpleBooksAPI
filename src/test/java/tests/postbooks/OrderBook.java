package tests.postbooks;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import routes.get.PostUserEndPoints;
import utilities.ReusableMethods;
import utilities.pojo.order.PojoBookOrder;

import static org.apache.commons.lang3.Validate.isInstanceOf;

public class OrderBook {
    PojoBookOrder payload = new PojoBookOrder();
    Faker fakeData = new Faker();
    PostUserEndPoints order = new PostUserEndPoints();
    private final int randomNum = ReusableMethods.generateRandomNumber();
    public PojoBookOrder insertPayload() {

        payload.setBookId(randomNum);
        payload.setCustomerName(fakeData.name().firstName() + " " + fakeData.name().lastName());
        return payload;
    }

    @Test
    public void OrderBookTest() {
        Response response = order.orderBook(insertPayload());
        response.then().statusCode(201)
                .log().status().and().log().body()
                .and().extract().response();

        JsonPath js = new JsonPath(response.asString());
        Boolean isCreated = js.getBoolean("created");
        String orderId = js.getString("orderId");

        Assert.assertEquals(isCreated,true);
        Assert.assertNotNull(orderId);
    }
}
