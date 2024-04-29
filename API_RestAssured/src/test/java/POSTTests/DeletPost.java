package POSTTests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeletPost {
    @Test
    public void deletePostTest() {
        // Set base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send DELETE request and validate response
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200)  // Assuming HTTP status code 200 for successful deletion
                .body(equalTo("{}")); // Assuming the response body for successful deletion is an empty JSON object
    }
}
