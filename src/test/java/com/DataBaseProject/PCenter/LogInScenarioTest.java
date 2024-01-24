package com.DataBaseProject.PCenter;


import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LogInScenarioTest {

    @Test
    void shouldLogIn() {
        // register first
        RestAssured.given()
                .body("""
                        {
                            "email": "testuser@my.com",
                            "password": "admin1234"
                        }
                        """)
                .header("content-type", "application/json")
                .when().post("/registration")
                .then().assertThat().statusCode(200);

        // try to access /me endpoint -- should not be allowed without login
        RestAssured.given()
                .when().get("/me")
                .then().assertThat().statusCode(403);


        // login, get token
        var token = RestAssured.given()
                .body("""
                        {
                            "email": "testuser@my.com",
                            "password": "admin1234"
                        }
                        """)
                .header("content-type", "application/json")
                .when().post("/login")
                .then().assertThat().statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertThat(token).isNotEmpty();

        // access /me with token -- should be allowed
        RestAssured.given().header("Authorization", "Bearer " + token)
                .when().get("/me")
                .then().assertThat().statusCode(200);

        // access /admin with USER token -- should not be allowed
        RestAssured.given().header("Authorization", "Bearer " + token)
                .when().get("/admin")
                .then().assertThat().statusCode(403);
    }
}
