package tests.getbooks;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ReusableMethods;
import routes.get.GetUserEndPoints;
public class GetNegativeBookTests {


    @Test(dataProvider = "invalidIds")
    public void getInvalidIdErrorTest(Object id) {
        GetUserEndPoints getUserEndPoints = new GetUserEndPoints();

        Response response = getUserEndPoints.getSpecificBookByIdResponse(id);
        response.then().statusCode(404)
                .log().body()
                .extract().response();
        JsonPath js = new JsonPath(response.asString());
        String getErrorMessage = js.getString("error");
        if (id instanceof Integer) {
            Assert.assertEquals(getErrorMessage, "No book with id " + id);

        }else {
            Assert.assertEquals(getErrorMessage, "No book with id NaN");
        }
    }

    @DataProvider(name = "invalidIds")
    public Object[][] provideInvalidIds() {
        return new Object[][]{
                {7},
                {"abc"},
                {true},
                {"!#@%"},
                {0},
                {-1}
                // Add more test data with various data types
        };
    }
}