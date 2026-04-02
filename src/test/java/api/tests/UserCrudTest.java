package api.tests;

import api.client.UserClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserCrudTest {
    UserClient userClient = new UserClient();
    @Test
    public void shouldCreateUser() {
        String body = """
        {
          "name": "John",
          "email": "john@test.com"
        }
        """;

        Response response = userClient.createUser(body);

        response.then().statusCode(201);
    }
}
