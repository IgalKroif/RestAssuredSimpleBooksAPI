package routes;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BookEndpoints {
    //MAIN URI also contains welcome message
    public static final String baseURI = "https://simple-books-api.glitch.me";
    //contains message status: ok
    public static final String getUrlStatus = baseURI + "/status";

    //GET ENDPOINTS
    //ACCEPTS QUERY PARAMS OF FICTION/ NON-FICTION
    public static String getAllBooks = baseURI + "/books";

    //Gets specified book by id
    public static String getBookById = getAllBooks + "/" +"{id}";

    //Gets all orders made -- requires authorization
    public static String getAllOrders = baseURI + "/orders";

    //Gets a specific order  -- requires authorization
    public static String getOrderById = baseURI + "/orders" +"/" + "{id}";

    //POST ENDPOINTS -- requires auth

    //Creates a new book order  -- requires authorization
    public static final String orderBook = baseURI + "/orders";

    //TO USE IT WE NEED A BODY OF clientName and clientEmail
    public static String authorize = baseURI + "/api-clients/";

    //PATCH ENDPOINTS
    //Updates a book order by id, requires auth, book id, and in the body, json of customer name.
    public static String updateAnOrder = getOrderById;

    //DELETE ENDPOINTS
    public static String deleteAnOrder = getOrderById;
}