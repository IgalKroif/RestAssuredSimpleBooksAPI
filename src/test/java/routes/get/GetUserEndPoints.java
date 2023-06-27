package routes.get;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import routes.BookEndpoints;
import routes.post.Authentication;
import utilities.ContentTypeEnums;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetUserEndPoints {
    ContentTypeEnums contentType = ContentTypeEnums.JSON;
    //@Test
    // Gets the base url with a message of welcome to simple booksAPI
    public Response getStatusResponse() {
        return given()
                .when().get(BookEndpoints.baseURI);
    }
    //@Test
    //Gets all books present in the API endpoint
    public Response getAllBooksResponse() {
        return given()
                .when().get(BookEndpoints.getAllBooks);
    }

    //Gets a single book based on id provided
    //@query param accepted between 1 and 6 otherwise
    //@return a message of : "no book with id {id}"
    public Response getSpecificBookByIdResponse(Object id) {
        //Faker faker = new Faker();
       //int randomNumber =  faker.number().numberBetween(1,100);
      return  given().log().all()
                .pathParam("id", id)
                .when().get(BookEndpoints.getBookById);
    }
    //@Test
    //token : 64dd492c8f2dc482b6442f826016981aed1da6253104085304edd688f0dfdd22
    public Response getOrderById(String id) {
        String staticToken = "97f1b027a201e6c3c07044a612a23fb569ad1e2ab665bb4804e373746b292367";
       return   given()
                .log().all()
                .contentType(contentType.getValue())
                .header("Authorization", "Bearer " + staticToken)
                .pathParam("id", id)
                .when().get(BookEndpoints.getOrderById);
        //JsonPath jsonPath = ReusableMethods.rawToJson(response.getBody().asString());
//        switch (response.statusCode()) {
//            case 200:
//
//
//                response.then().log().all();
//                break;
//            case 404:
//                response.then().assertThat().statusCode(404);
//                JsonPath js = new JsonPath(response.asString());
//                String expectedErrorMessage = js.getString("error");
//                assertThat(expectedErrorMessage, equalTo("No order with id " + id + "."));
//                break;
//        }
//            case 409:
//                response.then().assertThat().statusCode(409);
//                response.then().log().status().and().log().body();
//                System.out.println("status code: " + response.statusCode());
//                break;
//            default:
//                staticToken = Authentication.authenticateAndGetToken();
//                getOrderById(id);
//                break;
//        }

    }
    @Test
    public void test() {

        getOrderById("sadsad");
    }
}