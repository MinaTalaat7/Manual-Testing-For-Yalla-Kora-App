package Users;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class GetUsers {

    @Test
    public void GetUsers() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .log().all()
                .when()
                .get("/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("company.name", hasItem("Johns Group"));
    }

    @Test
    public void GetSpecificUser() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .log().all()
                .when()
                .get("/Users/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("address.geo.lat", equalTo("-37.3159"));
    }
    @Test
    public void GetUserNOTFound() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .log().all()
                .when()
                .get("/Users/198")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);

    }

    public static class GetAlbums {
        @Test
        public void GetUserAlbums() {
            RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

            // Send GET request to retrieve albums for user with ID 1
            String response = given()
                    .when()
                    .get("/users/1/albums")
                    .then()
                    .statusCode(200)
                    .extract()
                    .asString();
            System.out.println(response); // Print the response for debugging or logging purposes

        }
        @Test
        public void countAlbumIds() {
            RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

            // Send GET request to retrieve albums for user with ID 1
            String response = given()
                    .when()
                    .get("/users/1/albums")
                    .then()
                    .statusCode(200)
                    .extract()
                    .asString();

            // Parse the response as JSON and count the number of album IDs
            int albumCount = from(response).getList("id").size();

            // Print the count of album IDs
            System.out.println("Number of album IDs: " + albumCount);

            // Assert the count of album IDs (you can replace this with TestNG assertions)
            assert albumCount > 0 : "No album IDs found in the response";
        }

        @Test
        public void verifyAlbumsHaveTenIds() {
            RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

            given()
                    .when()
                    .get("/users/1/albums")
                    .then()
                    .statusCode(200)
                    .body("size()", equalTo(10)); // Ensure that the response contains exactly 10 albums
        }

    }
}
