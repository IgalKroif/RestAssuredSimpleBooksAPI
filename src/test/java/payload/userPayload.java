package payload;

import com.github.javafaker.Faker;

public class userPayload {
    static Faker faker = new Faker();
    public static String AuthPayload = "{\n" +
            "   \"clientName\": \"Postman1\",\n" +
            "   \"clientEmail\": \"" + faker.name().firstName() + "ZaPredator" + faker.number().digit() + "@example.com" + faker.number().randomNumber() + "\"\n" +
            "}";
}
