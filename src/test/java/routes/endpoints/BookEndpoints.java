package routes.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BookEndpoints {
    //MAIN URI also contains welcome message
    public static final String baseURI = "https://simple-books-api.glitch.me";

    //GET ENDPOINTS
    public static final String getAllBooks = "";
    public static final String getBookById = "";

    //contains message status: ok
    public static final String getUrlStatus = "";


    //POST ENDPOINTS

    //PATCH ENDPOINTS

    //DELETE ENDPOINTS
}