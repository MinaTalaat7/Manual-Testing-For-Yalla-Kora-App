package Users;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetTodos {
    @Test
    public void testCountCompletedTasks() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/todos")
                .then()
                .statusCode(200)
                .body("findAll { it.completed == true }.size()", equalTo(90))
                .body("findAll { it.completed == false }.size()", equalTo(110));
    }
}
