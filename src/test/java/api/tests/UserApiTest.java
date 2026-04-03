package api.tests;

import api.base.BaseApiTest;
import api.client.UserClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class UserApiTest extends BaseApiTest {
    private final UserClient userClient = new UserClient();

    // =========================
    // SMOKE TESTS
    // =========================

    @Test(groups = {"api", "smoke"})
    public void shouldReturnUser_whenValidUserIdProvided() {
        userClient.getUser(1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue());
    }

    @Test(groups = {"api", "smoke"})
    public void shouldReturnUsersList_whenRequestAllUsers() {
        userClient.getAllUsers()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    // =========================
    // VALIDATION TESTS
    // =========================

    @Test(groups = {"api", "regression"})
    public void shouldReturnJsonContentType_whenGetUser() {
        userClient.getUser(1)
                .then()
                .statusCode(200)
                .contentType(containsString("application/json"));
    }

    @Test(groups = {"api", "regression"})
    public void shouldContainRequiredFields_whenGetUser() {
        userClient.getUser(1)
                .then()
                .body("$", hasKey("id"))
                .body("$", hasKey("name"))
                .body("$", hasKey("email"));
    }

    @Test(groups = {"api", "regression"})
    public void shouldReturnValidEmailFormat_whenGetUser() {
        userClient.getUser(1)
                .then()
                .body("email", matchesRegex("^[A-Za-z0-9+_.-]+@(.+)$"));
    }

    // =========================
    // EDGE CASES
    // =========================

    @Test(groups = {"api", "regression"})
    public void shouldReturn404_whenUserIdDoesNotExist() {
        userClient.getUser(9999)
                .then()
                .statusCode(404);
    }

    @Test(groups = {"api", "regression"})
    public void shouldHandleZeroUserIdGracefully() {
        userClient.getUser(0)
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(404)));
    }

    @Test(groups = {"api", "regression"})
    public void shouldHandleNegativeUserIdGracefully() {
        userClient.getUser(-1)
                .then()
                .statusCode(anyOf(equalTo(400), equalTo(404)));
    }

    // =========================
    // COLLECTION VALIDATION
    // =========================

    @Test(groups = {"api", "regression"})
    public void shouldReturnUniqueUserIds_whenGetAllUsers() {
        Response response = userClient.getAllUsers();

        List<Integer> ids = response.jsonPath().getList("id");
        Assert.assertEquals(ids.size(), new HashSet<>(ids).size(),
                "User IDs should be unique");
    }

    @Test(groups = {"api", "regression"})
    public void shouldContainValidEmails_whenGetAllUsers() {
        userClient.getAllUsers()
                .then()
                .body("email", everyItem(containsString("@")));
    }

    @Test(groups = {"api", "regression"})
    public void shouldHaveConsistentStructure_whenGetAllUsers() {
        userClient.getAllUsers()
                .then()
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("email"));
    }

    // =========================
    // PERFORMANCE
    // =========================

    @Test(groups = {"api", "performance"})
    public void shouldRespondWithinAcceptableTime_whenGetUser() {
        userClient.getUser(1)
                .then()
                .time(lessThan(2000L));
    }


}
