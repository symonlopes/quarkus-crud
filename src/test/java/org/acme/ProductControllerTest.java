package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.acme.model.Product;

import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ProductControllerTest {

    @Test
    public void testCreateProduct() {

        var product = Product.builder()
                .description("Test Description")
                .details("Test Details")
                .build();
        given()
                .body(product)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/products")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("description", is("Test Description"));
    }

    @Test
    public void testCreateProductWithEmptyDescription() {
        var product = Product.builder()
                .description("")
                .build();
        given()
                .body(product)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/products")
                .then()
                .statusCode(400)
                .body("code", is("INVALID_DESCRIPTION"));
    }

    @Test
    public void testCreateAndGetProduct() {
        var product = Product.builder()
                .description("Test Description for Get")
                .build();

        Integer id = given()
                .body(product)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/products")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when().get("/products/" + id)
                .then()
                .statusCode(200)
                .body("id", is(id))
                .body("description", is("Test Description for Get"));
    }

    @Test
    public void testGetProductNotFound() {
        given()
                .when().get("/products/999999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateProductWithShortDetails() {
        var product = Product.builder()
                .description("Valid Description")
                .details("12") // Less than 3
                .build();

        given()
                .body(product)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/products")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateProductWithLongDetails() {
        var product = Product.builder()
                .description("Valid Description")
                .details("a".repeat(10001)) // More than 10000
                .build();

        given()
                .body(product)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/products")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateProductWithValidDetails() {
        var product = Product.builder()
                .description("Valid Description")
                .details("Valid details content")
                .build();

        given()
                .body(product)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/products")
                .then()
                .statusCode(201)
                .body("details", is("Valid details content"));
    }

    @Test
    public void testProductBuilder() {
        var product = Product.builder()
                .description("Builder Description")
                .details("Builder Details")
                .build();

        given()
                .body(product)
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/products")
                .then()
                .statusCode(201)
                .body("description", is("Builder Description"))
                .body("details", is("Builder Details"));
    }
}
