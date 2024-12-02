package com.thetestingacademy.CURDOperations.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITesting005_BDDStyle_GET {
    @Test
    public void test_Get_Request_Positive()
    {
        String pinCode="20171";
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/us/"+pinCode)
                .when()
                .log()
                .all()
                .get()
                .then()
                .log()
                .all()
                .statusCode(200);
    }
    @Test
    public void test_Get_Request_Negative()
    {
        String pinCode="-1";
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/us/"+pinCode)
                .when()
                .log()
                .all()
                .get()
                .then()
                .log()
                .all()
                .statusCode(404);
    }
}
