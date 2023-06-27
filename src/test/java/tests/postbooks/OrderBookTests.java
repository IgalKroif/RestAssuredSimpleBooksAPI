package tests.postbooks;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import routes.get.GetUserEndPoints;
import routes.post.PostUserEndPoints;
import utilities.ReusableMethods;
import utilities.pojo.order.PojoBookOrder;

import java.util.Map;

import static org.apache.commons.lang3.Validate.isInstanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrderBookTests {
    PojoBookOrder payload = new PojoBookOrder();
    Faker fakeData = new Faker();
    PostUserEndPoints order = new PostUserEndPoints();
    GetUserEndPoints newSpecifeBook = new GetUserEndPoints();
    private final int randomNum = ReusableMethods.generateRandomNumber();
    public PojoBookOrder insertPayload() {

        payload.setBookId(randomNum);
        payload.setCustomerName(fakeData.name().firstName() + " " + fakeData.name().lastName());
        return payload;
    }
    public Response orderBookTest() {
        //ORDER A BOOK BY A RANDOM ID BETWEEN 1 AND 6
        Response response = order.orderBook(insertPayload());
        response.then().statusCode(201)
                .log().status().and().log().body()
                .and().extract().response();

        JsonPath js = new JsonPath(response.asString());
        Boolean isCreated = js.getBoolean("created");
        String orderId = js.getString("orderId");

        Assert.assertEquals(isCreated,true);
        Assert.assertNotNull(orderId);
        return response;

    }
    @Test
    public void orderSingleBookTest() {
         orderBookTest();
    }
    @Test
    public void orderBookAndGetTheOrderTest() {
        //ORDER A BOOK BY A RANDOM ID BETWEEN 1 AND 6
        Response response = orderBookTest();

        JsonPath js = new JsonPath(response.asString());
        Boolean isCreated = js.getBoolean("created");
        String orderId = js.getString("orderId");

        Assert.assertEquals(isCreated,true);
        Assert.assertNotNull(orderId);

        //REQUEST THE BOOK ORDERED ABOVE AND ASSERT THAT THE ID'S MATCHED
        response = newSpecifeBook.getOrderById(orderId);
        js = new JsonPath(response.asString());
        String id = js.getString("id");
        String customerName = js.getString("customerName");
        switch (response.statusCode()) {
            case 200:
                response.then().statusCode(200)
                        .log().all()
                        .extract().response();

                Assert.assertEquals(orderId,id);
                Assert.assertEquals(payload.getCustomerName(),customerName);
                // Parse the response body as a JSON object using Gson
                Gson gson = new Gson();
                TypeToken<Map<String, Object>> typeToken = new TypeToken<>() {
                };
                Map<String, Object> jsonMap = gson.fromJson(response.asString(), typeToken.getType());

                Assert.assertTrue(jsonMap.containsKey("id"));
                Assert.assertTrue(jsonMap.containsKey("bookId"));
                Assert.assertTrue(jsonMap.containsKey("customerName"));
                Assert.assertTrue(jsonMap.containsKey("createdBy"));
                Assert.assertTrue(jsonMap.containsKey("quantity"));
                Assert.assertTrue(jsonMap.containsKey("timestamp"));
                break;

            case 404:
                response.then().assertThat().statusCode(404);
                js = new JsonPath(response.asString());
                String expectedErrorMessage = js.getString("error");
                assertThat(expectedErrorMessage, equalTo("No order with id " + id + "."));
                break;
        }
    }
}