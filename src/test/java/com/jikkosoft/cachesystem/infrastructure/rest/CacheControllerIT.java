package com.jikkosoft.cachesystem.infrastructure.rest;

import com.jikkosoft.cachesystem.AbstractRedisIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CacheControllerIT extends AbstractRedisIntegrationTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/cache";
    }

    @Test
    void createAndFetchEntry() {
        Map<String, Object> body = Map.of(
                "key", "greeting",
                "value", "hola",
                "ttl", "PT5M");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post()
                .then()
                .statusCode(201)
                .body("key", equalTo("greeting"))
                .body("value", equalTo("hola"));

        RestAssured.given()
                .when().get("/greeting")
                .then()
                .statusCode(200)
                .body("value", equalTo("hola"));
    }

    @Test
    void returns404ForMissingKey() {
        RestAssured.given()
                .when().get("/missing-key-xyz")
                .then()
                .statusCode(404)
                .body("title", equalTo("Cache entry not found"));
    }

    @Test
    void returns400WhenBlankKey() {
        Map<String, Object> body = Map.of("key", "", "value", "x");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post()
                .then()
                .statusCode(400);
    }

    @Test
    void deleteReturns204() {
        Map<String, Object> body = Map.of("key", "del-me", "value", 1);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post()
                .then().statusCode(201);

        RestAssured.given()
                .when().delete("/del-me")
                .then().statusCode(204);
    }
}
