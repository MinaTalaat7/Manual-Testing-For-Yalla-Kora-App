package POSTTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutPost {
    @Test
    public void putUserTest() {
        // Set base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Define the JSON body for the PUT request
        String requestBody = "{\"id\": 1, \"title\": \"fo98595o\", \"body\": \"bar\", \"userId\": 1}";

        // Send PUT request and validate response
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200); // Assuming HTTP status code 200 for successful update
    }
}
