package api.tests;

import api.base.BaseApiTest;
import api.utils.ApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends BaseApiTest {

    @Test
    public void shouldGetUserById() {
        Response response = ApiClient.get("/users/1");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("name"));
    }
}
