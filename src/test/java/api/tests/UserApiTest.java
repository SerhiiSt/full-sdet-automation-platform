package api.tests;

import api.base.BaseApiTest;
import api.client.UserClient;
import api.models.User;
import api.utils.ApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends BaseApiTest {
    UserClient userClient = new UserClient();

    @Test
    public void shouldGetUserById() {
        Response response = ApiClient.get("/users/1");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("name"));
    }

    @Test
    public void shouldGetUser() {
        Response response = userClient.getUser(1);

        response.then().statusCode(200);
    }

}
