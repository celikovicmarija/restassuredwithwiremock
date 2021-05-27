package restAssuredWithWireMock.proxy;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserProxy {

    private static final String BASE_URL="http://localhost:8080";
    public static Response getAuthorizedUser(){
        final Response response= RestAssured.given().log().all()
                .baseUri(BASE_URL)
                .when()
                .get("/get/authorized/user")
                .then()
                .log().all()
                .extract().response();
        return response;
    }
}
