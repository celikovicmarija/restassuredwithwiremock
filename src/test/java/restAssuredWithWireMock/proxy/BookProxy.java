package restAssuredWithWireMock.proxy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import restAssuredWithWireMock.common.Load;
import restAssuredWithWireMock.model.Book;
import restAssuredWithWireMock.model.ListOfBooks;

import java.net.URI;

import static io.restassured.RestAssured.given;

public class BookProxy {

    private static final String BASE_URL="http://localhost:8080";
    private static final URI ADD_BOOKS=URI.create("requests/postRequest.json");
    private static final URI UPDATE_BOOKS=URI.create("requests/putRequest.json");

    public static ListOfBooks getAllBooks(){
        return given()
                .baseUri(BASE_URL)
                .when()
                .get("/find/all/books")
                .then()
                .log().all()
                .extract().response()
                .as(ListOfBooks.class);
    }

    public static Book getOneBook(){
        return given().baseUri(BASE_URL)
                .pathParam("id",1)
                .when()
                .get("/get/oneBook/{id}")
                .then()
                .log().all()
                .extract().response()
                .as(Book.class); //deserijalizacija
        //.relaxedHTTPSValidation() posle given ako radis sa https
    }
    public static Book getOneBookWithQueryParam(){
        return given().baseUri(BASE_URL)
                .queryParam("author","Alan")
                .when()
                .get("/get/oneBook/withQueryParam")
                .then()
                .log().all()
                .extract().response()
                .as(Book.class); //deserijalizacija
    }

    public static Response deleteBook(){
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .delete("/deleteBook")
                .then()
                .log().all()
                .extract().response();

    }

    public static Response addBook(){
        String body= Load.aFile(ADD_BOOKS)
                .replace("#ID#","4")
                .replace("#AUTHOR#","Gojko Adzic")
                .replace("#BOOKTITLE#","Fifty Quick Ideas to Improve Your Tests");
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/addBook")
                .then()
                .log().all()
                .extract().response();

    }


    public static Response updateBook(){
        String body= Load.aFile(ADD_BOOKS)
                .replace("#BOOKTITLE#","Fifty Quick Ideas to Improve Your User Stories");
        return given()
                .log().all()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/updateBook")
                .then()
                .log().all()
                .extract().response();

    }



}
