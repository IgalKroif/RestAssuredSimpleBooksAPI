package routes.post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.userPayload;
import routes.BookEndpoints;
import utilities.ContentTypeEnums;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Authentication {
    private static String token;
    static ContentTypeEnums myContentType = ContentTypeEnums.JSON;
    public static String authenticateAndGetToken() {
        token = given()
                .contentType(myContentType.getValue())
                .body(userPayload.AuthPayload)
                .when()
                .post(BookEndpoints.authorize)
                .then()
                .extract()
                .path("accessToken").toString();
        System.out.println(token);

        return token;
    }
}
