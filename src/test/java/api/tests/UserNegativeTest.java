package api.tests;

import api.client.UserClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserNegativeTest {
    UserClient userClient = new UserClient();

    @Test
    public void shouldReturn404ForInvalidUser() {
        Response response = userClient.getUser(9999);

        response.then().statusCode(404);
    }
}
