package POSTTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostAddNewPost {
    @Test
    public void postUserTest() {
        // Set base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Define the JSON body
        String requestBody = "{\"id\": 1, \"title\": \"software tester\", \"body\": \"Mina Talaat\", \"userId\": 1}";

        // Send POST request and validate response
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/Users")
                .then()
                .statusCode(201); // Assuming HTTP status code 201 for successful creation
    }

}
