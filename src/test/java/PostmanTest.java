import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class PostmanTest {

    private static final String BASE_URL = "https://postman-echo.com";
    private static final String BASE_REQUEST_BODY = "{\"name\":\"Alina\",\"course\":\"AQA\"}";

    @Test
    void getReturns200() {
        given()
                .baseUri(BASE_URL)
                .header("x-lesson", "8")
        .when()
                .get("/get")
        .then()
                .statusCode(200);
    }

    @Test
    void postReturns200AndBody() {
        given()
                .baseUri(BASE_URL)
                .header("x-lesson", "8")
                .contentType("application/json")
                .body(BASE_REQUEST_BODY)
        .when()
                .post("/post")
        .then()
                .statusCode(200)
                .body("data", equalTo(BASE_REQUEST_BODY))
                .body("json.name", equalTo("Alina"))
                .body("json.course", equalTo("AQA"));
    }

    @Test
    void putReturns200AndBody() {
        given()
                .baseUri(BASE_URL)
                .header("x-lesson", "8")
                .contentType("application/json")
                .body(BASE_REQUEST_BODY)
        .when()
                .put("/put")
        .then()
                .statusCode(200)
                .body("data", equalTo(BASE_REQUEST_BODY))
                .body("json.name", equalTo("Alina"))
                .body("json.course", equalTo("AQA"));
    }

    @Test
    void patchReturns200AndBody() {
        given()
                .baseUri(BASE_URL)
                .header("x-lesson", "8")
                .contentType("application/json")
                .body(BASE_REQUEST_BODY)
        .when()
                .patch("/patch")
        .then()
                .statusCode(200)
                .body("data", equalTo(BASE_REQUEST_BODY))
                .body("json.name", equalTo("Alina"))
                .body("json.course", equalTo("AQA"));
    }

    @Test
    void deleteReturns200AndEmptyBody() {
        given()
                .baseUri(BASE_URL)
                .header("x-lesson", "8")
        .when()
                .delete("/delete")
        .then()
                .statusCode(200)
                .body("data", equalTo(""))
                .body("json", nullValue());
    }

    @Test
    void headReturns200AndEmptyBody() {
        given()
                .baseUri(BASE_URL)
                .header("x-lesson", "8")
        .when()
                .head("/get")
        .then()
                .statusCode(200)
                .body(equalTo(""));
    }

    @Test
    void optionsReturns200AndAllowedMethods() {
        given()
                .baseUri(BASE_URL)
        .when()
                .options("/get")
        .then()
                .statusCode(200)
                .body(equalTo("GET,HEAD,PUT,POST,DELETE,PATCH"));
    }
}
