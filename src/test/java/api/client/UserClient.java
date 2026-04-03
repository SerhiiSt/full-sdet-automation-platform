package api.client;
import api.specs.ApiSpec;

import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class UserClient {
    public Response getUser(int id) {
        return given()
                .spec(ApiSpec.getRequestSpec())
                .when()
                .get("/users/" + id);
    }

    public Response getAllUsers() {
        return given()
                .spec(ApiSpec.getRequestSpec())
                .when()
                .get("/users");
    }

    public Response createUser(String body) {
        return given()
                .spec(ApiSpec.getRequestSpec())
                .body(body)
                .when()
                .post("/users");
    }

    public Response updateUser(int id, String body) {
        return given()
                .spec(ApiSpec.getRequestSpec())
                .body(body)
                .when()
                .put("/users/" + id);
    }

    public Response deleteUser(int id) {
        return given()
                .spec(ApiSpec.getRequestSpec())
                .when()
                .delete("/users/" + id);
    }
}
