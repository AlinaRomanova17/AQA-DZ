import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.equalTo;

public class PostmanTest {

    private static final String BASE_URL = "https://postman-echo.com";
    private static final String BASE_REQUEST_BODY = "{\"FirstName\":\"Alina\",\"LastName\":\"Romanova\"}";

    @Test
    void get() {
        RestAssured.baseURI = BASE_URL;
        given()
                .queryParam("FirstName", "Alina")
                .queryParam("LastName", "Romanova")
        .when()
                .get("/get")
        .then()
                .statusCode(200)
                .body("args.FirstName", equalTo("Alina"))
                .body("args.LastName", equalTo("Romanova"));
    }

    @Test
    void post() {
        given()
                .baseUri(BASE_URL)
                .header("FirstName", "Alina")
                .header("LastName", "Romanova")
                .contentType("application/json")
                .body(BASE_REQUEST_BODY)
        .when()
                .post("/post")
        .then()
                .statusCode(200)
                .body("json.FirstName", equalTo("Alina"))
                .body("json.LastName", equalTo("Romanova"));
    }

    @Test
    void put() {
        given()
                .baseUri(BASE_URL)
                .header("FirstName", "Alina")
                .header("LastName", "Romanova")
                .contentType("application/json")
                .body(BASE_REQUEST_BODY)
        .when()
                .put("/put")
        .then()
                .statusCode(200)
                .body("json.FirstName", equalTo("Alina"))
                .body("json.LastName", equalTo("Romanova"));
    }

    @Test
    void patch() {
        given()
                .baseUri(BASE_URL)
                .header("FirstName", "Alina")
                .header("LastName", "Romanova")
                .contentType("application/json")
                .body(BASE_REQUEST_BODY)
        .when()
                .patch("/patch")
        .then()
                .statusCode(200)
                .body("json.FirstName", equalTo("Alina"))
                .body("json.LastName", equalTo("Romanova"));
    }

    @Test
    void delete() {
        RestAssured.baseURI = BASE_URL;
        given()
                .queryParam("FirstName", "Alina")
                .queryParam("LastName", "Romanova")
        .when()
                .delete("/delete")
        .then()
                .statusCode(200)
                .body("args.FirstName", equalTo("Alina"))
                .body("args.LastName", equalTo("Romanova"));
    }
}
