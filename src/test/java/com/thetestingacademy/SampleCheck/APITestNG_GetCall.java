package com.thetestingacademy.SampleCheck;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITestNG_GetCall {

    @Test
    public void getRequest()
    {
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/1")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);
    }
}
