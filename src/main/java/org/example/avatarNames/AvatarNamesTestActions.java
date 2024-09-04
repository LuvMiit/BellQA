package org.example.avatarNames;


import org.example.dto.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AvatarNamesTestActions {

    public static Response getResponse(String url){
        return given()
                .when()
                .get(url)
                .then()
                .body("data", notNullValue())
                .statusCode(200)
                .extract().response().as(Response.class);

    }
}
