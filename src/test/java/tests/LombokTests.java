package tests;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import specs.Specs;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;


public class LombokTests {


    @Test
    void createUser() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\", \"id\": \"598\", \"createdAt\": \"2022-06-07T11:53:47.580Z\" }";

        given()
                .spec(Specs.request)
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().status()
                .log().body()
                .body("name", is("morpheus"));
    }


    @Test
    void checkEmailGroovy() {
        given()
                .spec(Specs.request)
                .get("/users?page=2")
                .then()
                .spec(Specs.response)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("byron.fields@reqres.in"))
                .body("data.findAll{it.first_name}.first_name.flatten()", hasItem("Byron"));
    }
}
