package api.tests;

import api.base.BaseApiTest;
import api.client.UserClient;
import api.models.User;
import api.utils.ApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class UserApiTest extends BaseApiTest {
    UserClient userClient = new UserClient();

    @Test
    public void shouldGetUserByIdUsingClientLayer() {
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

    @Test
    public void shouldGetUserByIdUsingRawApiClient() {
        userClient.getUser(1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue());
    }

    @Test
    public void shouldGetAllUsers() {
        userClient.getAllUsers()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

}
