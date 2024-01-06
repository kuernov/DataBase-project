package com.DataBaseProject.PCenter;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BasicFuntionsTest {
    @Test
    void shouldAddCategoryAndProduct(){
        RestAssured.given()
                .body("""
                        {
                            "name":"CPU",
                            "description":"Procesory"
                                                
                        }
                        """)
                .header("content-type", "application/json")
                .when().post("/categories/create")
                .then().assertThat().statusCode(201);


        RestAssured.given()
                .body("""
                        {
                            "name":"Intel i7",
                            "description":"CPU",
                            "price":7000,
                            "currentQuantity":10,
                            "category": {
                                "id":1,
                                "name":"CPU",
                                "description":"Procesory"
                            }
                        }
                        """)
                .header("content-type", "application/json")
                .when().post("/products")
                .then().assertThat().statusCode(201);

        RestAssured.given()
                .body("""
                        {
                            "name":"Intel i5",
                            "description":"CPU",
                            "price":5000,
                            "currentQuantity":5,
                            "category":1
                        }
                        """)
                .header("content-type", "application/json")
                .when().post("/products")
                .then().assertThat().statusCode(400);

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

        RestAssured.given().header("Authorization", "Bearer " + token)
                .when().get("/me")
                .then().assertThat().statusCode(200);

        RestAssured.given()
                .queryParam("id", 1)
                .queryParam("quantity", 2)
                .header("Authorization", "Bearer " + token)  // Assuming you have a valid token
                .when().post("/cart/add")
                .then().assertThat().statusCode(200);

        String cartResponse = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when().get("/cart")
                .then().assertThat().statusCode(200)
                .extract().asString();
        assertEquals(cartResponse, "{\"id\":1,\"totalPrice\":14000.00,\"cartItems\":[{\"id\":1,\"product\":{\"id\":1,\"name\":\"Intel i7\",\"price\":7000.00,\"description\":\"CPU\",\"imageURL\":null,\"currentQuantity\":10,\"category\":{\"id\":1,\"name\":\"CPU\",\"description\":\"Procesory\"}},\"quantity\":2}]}");



    }
}
