package POSTTests;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class GetPosts {
    @Test
    public void Getallposts() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .log().all()
                .when()
                .get("/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("[0].userId", equalTo(1))
                .body("[0].title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .body("title", hasItem("fuga nam accusamus voluptas reiciendis itaque"))
                .body("title", not(hasItem("Software Tester ")))
                .body("findAll { it.userId == 1 }.size()", equalTo(10))
                // .body("findAll { it.userId == 1 }.id", contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
                .body("title ", is(not(empty())))
                .body("id ", hasSize(100))
                .body("$", everyItem(hasKey("userId")));

    }

    @Test
    public void GetSpecificPost() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .time(lessThan(5000L));

    }

    @Test
    public void GetPostNotFound() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .log().all()
                .when()
                .get("/posts/111")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);

    }

    @Test
    public void GetCommentsForPost() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .log().all()
                .when()
                .get("/posts/1/comments")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("postId", everyItem(equalTo(1))) // Check that every comment has postId equal to 1
                .body("", hasSize(5)); // Check that there are exactly 5 comments returned
    }

    @Test
    public void GetCommentForPostAndId() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .log().all()
                .queryParam("postId", 1)
//                .queryParam("id", 2)
                .when()
                .get("/comments")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }


    @Test
    public void testPostIds() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("userId.unique().size()", equalTo(10)); // Assuming there are 10 unique user IDs
    }


    @Test
    public void testEveryUserIdIsRepeated10Times() {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("userId.collect { it.toString() }.countBy { it }.values()", everyItem(equalTo(10)));
    }



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