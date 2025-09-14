package com.project2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class SecondRest {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://dummyjson.com";

    }
    @Test
    public void getAllProducts() {
        when()
                .get("/products")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("total", greaterThan(0));
    }
    @Test
    public void getSingleProduct() {
        when()
                .get("/products/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue());
    }
    @Test
    public void searchProducts() {
        given()
                .queryParam("q", "phone")
                .when()
                .get("/products/search")
                .then()
                .statusCode(200)
                .body("products", everyItem(hasKey("price")));
    }

}
