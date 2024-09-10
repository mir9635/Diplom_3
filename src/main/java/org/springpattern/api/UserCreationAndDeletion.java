package org.springpattern.api;

import org.springpattern.User;

import static org.hamcrest.CoreMatchers.equalTo;
import static io.restassured.RestAssured.*;

import io.restassured.response.Response;


public class UserCreationAndDeletion {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/auth";
    private static final String REGISTRATION_URL = "/register";
    User user;


    public Response createUniqueUser(User user) {
        this.user = user;
        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(user)
                .when()
                .post(REGISTRATION_URL);
    }

    public String authorizationUser() {
        Response response =
                given()
                        .baseUri(BASE_URL)
                        .contentType("application/json")
                        .body("{\"email\": \"" + user.getEmail() + "\", \"password\": \"" + user.getPassword() + "\"}")
                        .when()
                        .post("/login");

        if (response.statusCode() != 200) {
            System.err.println("Ошибка при авторизации: " + response.body().asString());
            throw new RuntimeException("Authorization failed. Status code: " + response.statusCode());
        }

        response.then()
                .statusCode(200)
                .body("success", equalTo(true));

        return response.jsonPath().getString("accessToken");
    }

    public String authorizationUser(User user1) {
        Response response =
                given()
                        .baseUri(BASE_URL)
                        .contentType("application/json")
                        .body("{\"email\": \"" + user1.getEmail() + "\", \"password\": \"" + user1.getPassword() + "\"}")
                        .when()
                        .post("/login");

        if (response.statusCode() != 200) {
            System.err.println("Ошибка при авторизации: " + response.body().asString());
            throw new RuntimeException("Authorization failed. Status code: " + response.statusCode());
        }

        response.then()
                .statusCode(200)
                .body("success", equalTo(true));

        return response.jsonPath().getString("accessToken");
    }

    public void deleteUser(String accessToken) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .delete("/user"); // Использование относительного URL

        if (response.statusCode() != 202) {
            System.err.println("Ошибка при удалении пользователя: " + response.body().asString());
        }
    }

}
