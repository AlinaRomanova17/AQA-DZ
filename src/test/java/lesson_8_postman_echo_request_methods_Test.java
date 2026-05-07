import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.nullValue;

public class lesson_8_postman_echo_request_methods_Test {

    private static final String BASE_URL = "https://postman-echo.com";
    private static final String BASE_REQUEST_BODY = "{\"name\":\"Alina\",\"course\":\"AQA\"}";

    @Test
    void get_method_shouldReturn200_andExpectedBody() {
        given()
                .baseUri(BASE_URL)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .header("x-lesson", "8")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    void post_method_shouldReturn200_andExpectedBody() {
        given()
                .baseUri(BASE_URL)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .header("x-lesson", "8")
                .contentType("application/json")
                .body(BASE_REQUEST_BODY)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("data", equalTo(BASE_REQUEST_BODY))
                .body("json.name", equalTo("Alina"))
                .body("json.course", equalTo("AQA"));
    }

    @Test
    void put_method_shouldReturn200_andExpectedBody() {
        given()
                .baseUri(BASE_URL)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .header("x-lesson", "8")
                .contentType("application/json")
                .body(BASE_REQUEST_BODY)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("data", equalTo(BASE_REQUEST_BODY))
                .body("json.name", equalTo("Alina"))
                .body("json.course", equalTo("AQA"));
    }

    @Test
    void patch_method_shouldReturn200_andExpectedBody() {
        given()
                .baseUri(BASE_URL)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .header("x-lesson", "8")
                .contentType("application/json")
                .body(BASE_REQUEST_BODY)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("data", equalTo(BASE_REQUEST_BODY))
                .body("json.name", equalTo("Alina"))
                .body("json.course", equalTo("AQA"));
    }

    @Test
    void delete_method_shouldReturn200_andExpectedBody() {
        given()
                .baseUri(BASE_URL)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .header("x-lesson", "8")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("data", equalTo(""))
                .body("json", nullValue());
    }

    @Test
    void head_method_shouldReturn200_andEmptyBody() {
        given()
                .baseUri(BASE_URL)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .header("x-lesson", "8")
                .when()
                .head("/get")
                .then()
                .statusCode(200)
                .body(equalTo(""));
    }

    @Test
    void options_method_shouldReturn200_andAllowedMethodsBody() {
        given()
                .baseUri(BASE_URL)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .options("/get")
                .then()
                .statusCode(200)
                .body(equalTo("GET,HEAD,PUT,POST,DELETE,PATCH"));
    }
}
