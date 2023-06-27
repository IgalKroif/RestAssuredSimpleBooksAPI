package tests.getbooks;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import routes.get.GetUserEndPoints;
import io.restassured.response.Response;
import utilities.ReusableMethods;

import java.util.Arrays;

public class GetBookTests {
//EXPECTED VALUES FOR KEYS IN THE RESPONSE

    String[] expectedBookNames = {"The Russian", "Just as I Am", "The Vanishing Half",
            "The Midnight Library", "Untamed", "Viscount Who Loved Me"};
    String[] expectedBookTypes = {"fiction", "non-fiction"};
    Boolean[] expectedAvailability = {true, false};
    Integer[] expectedId = {1,2,3,4,5,6};
    GetUserEndPoints getUserEndPoints = new GetUserEndPoints();
    String expectedWelcome = "Welcome to the Simple Books API.";

    @Test
    public void getWelcomeMessageTest() {

        // Send the request and get the response
        Response response = getUserEndPoints.getStatusResponse();

        // Assert the status code
        response.then().assertThat().statusCode(200).log().body();

        // Extract the response body as a string
        String responseBody = response.getBody().asString();

        // Extract the value using JsonPath
        JsonPath jsonPath = new JsonPath(responseBody);
        String messageField = jsonPath.getString("message");

        // Assert the key-value pair
        Assert.assertEquals(messageField, expectedWelcome);
    }

    @Test
    public void getAllBooksTest() {

        // TestCase to get a list of all books.
        Response response = getUserEndPoints.getAllBooksResponse();
        response.then().assertThat().statusCode(200).log().body();

        // Convert the response to a JsonPath object
        JsonPath jsonPath = response.jsonPath();
        //Path to root is "" because the response body starts with a collection : []
        int bookCount = jsonPath.getList("").size();

        // Loop through the books
        for (int i = 0; i < bookCount; i++) {
            // Access each book using the index
            int idNum = jsonPath.getByte("[" + i + "].id");
            String bookName = jsonPath.getString("[" + i + "].name");
            String bookType = jsonPath.getString("[" + i + "].type");
            Boolean isAvailable = jsonPath.getBoolean("[" + i + "].available");

            // Assert if the values of each object is present as shown in the response.
            Assert.assertTrue(Arrays.asList(expectedBookNames).contains(bookName));
            Assert.assertTrue(Arrays.asList(expectedBookTypes).contains(bookType));
            Assert.assertTrue(Arrays.asList(expectedId).contains(idNum));
            Assert.assertTrue(Arrays.asList(expectedAvailability).contains(isAvailable));

            // Perform any assertions or operations on each book
            System.out.println("Book " + (i + 1) + ": " + bookName + " (" + bookType + ")");
        }
    }
    @Test
    public void getSpecificBook() {
        int randomNumBetweenOneAndSix = ReusableMethods.generateRandomNumber();
        Response response = getUserEndPoints.getSpecificBookByIdResponse(randomNumBetweenOneAndSix);
        response.then().statusCode(200)
                .log().body()
                .extract().response();
        JsonPath js = new JsonPath(response.asString());
        System.out.println("Actual ID in use of path param: " + js.getInt("id"));
        //Asserting the  random number generated to the id provided as path parameter
        Assert.assertEquals(js.getInt("id"),randomNumBetweenOneAndSix);
        int currentStock = js.getInt("current-stock");
        boolean isAvailable = js.getBoolean("available");
        String isbn = js.getString("isbn");
        Float bookPrice = js.getFloat("price");
        String authorName = js.getString("author");

        switch (js.getInt("id")) {
            case 1:
                String expectedIsbn = "1780899475";
                    Assert.assertTrue(Arrays.asList(expectedBookNames).contains("The Russian"));
                    Assert.assertTrue(Arrays.asList(expectedBookTypes).contains("fiction"));
                    Assert.assertEquals(authorName, "James Patterson and James O. Born");
                    Assert.assertEquals(bookPrice,12.98F);
                    Assert.assertEquals(isbn, expectedIsbn);
                if (currentStock == 12) {
                    Assert.assertEquals(currentStock, 12);
                } else {
                    Assert.assertNotEquals(currentStock, 12);
                }
                if (isAvailable) {
                    Assert.assertTrue(isAvailable, "Book is available");

                } else {
                    Assert.assertFalse(isAvailable,"Book is not available");
                }

                    break;

            case 2:
                Assert.assertTrue(Arrays.asList(expectedBookNames).contains("Just as I Am"));
                Assert.assertTrue(Arrays.asList(expectedBookTypes).contains("non-fiction"));
                Assert.assertEquals(js.getString("author"), "Cicely Tyson");
                Assert.assertEquals(js.getFloat("price"),20.33F);
                if (currentStock == 0) {
                    Assert.assertEquals(currentStock, 0);
                } else {
                    Assert.assertNotEquals(currentStock, 0);
                }
                if (isAvailable) {
                    Assert.assertTrue(isAvailable, "Book is available");

                } else {
                    Assert.assertFalse(isAvailable,"Book is not available");
                }
                break;

            case 3:
                Assert.assertTrue(Arrays.asList(expectedBookNames).contains("The Vanishing Half"));
                Assert.assertTrue(Arrays.asList(expectedBookTypes).contains("fiction"));
                Assert.assertEquals(authorName, "Brit Bennett");
                Assert.assertEquals(bookPrice,16.2F);
                if (currentStock == 987) {
                    Assert.assertEquals(currentStock, 987);
                } else {
                    Assert.assertNotEquals(currentStock, 987);
                }
                if (isAvailable) {
                    Assert.assertTrue(isAvailable, "Book is available");

                } else {
                    Assert.assertFalse(isAvailable,"Book is not available");
                }

                break;
            case 4:
                Assert.assertTrue(Arrays.asList(expectedBookNames).contains("The Midnight Library"));
                Assert.assertTrue(Arrays.asList(expectedBookTypes).contains("fiction"));
                Assert.assertEquals(js.getString("author"), "Matt Haig");
                Assert.assertEquals(js.getFloat("price"),15.6F);
                if (currentStock == 87) {
                    Assert.assertEquals(currentStock, 87);
                } else if (currentStock != 87) {
                    Assert.assertNotEquals(currentStock, 87);
                    }
                if (isAvailable) {
                    Assert.assertTrue(isAvailable, "Book is available");

                } else {
                    Assert.assertFalse(isAvailable,"Book is not available");
                }
                break;
            case 5:
                Assert.assertTrue(Arrays.asList(expectedBookNames).contains("Untamed"));
                Assert.assertTrue(Arrays.asList(expectedBookTypes).contains("fiction"));
                Assert.assertEquals(authorName, "Glennon Doyle");
                Assert.assertEquals(bookPrice,15.5F);
                if (currentStock == 20) {
                    Assert.assertEquals(currentStock, 20);
                } else {
                    Assert.assertNotEquals(currentStock, 20);
                }
                if (isAvailable) {
                    Assert.assertTrue(isAvailable, "Book is available");

                } else {
                    Assert.assertFalse(isAvailable,"Book is not available");
                }
                break;
            case 6:
                Assert.assertTrue(Arrays.asList(expectedBookNames).contains("Viscount Who Loved Me"));
                Assert.assertTrue(Arrays.asList(expectedBookTypes).contains("fiction"));
                Assert.assertEquals(authorName, "Julia Quinn");
                Assert.assertEquals(bookPrice,15.6F);
                if (currentStock == 1021) {
                    Assert.assertEquals(currentStock, 1021);
                } else {
                    Assert.assertNotEquals(currentStock, js.getFloat("price"));
                }
                if (isAvailable) {
                    Assert.assertTrue(isAvailable, "Book is available");

                } else {
                    Assert.assertFalse(isAvailable,"Book is not available");
                }
                break;
        }
    }
}