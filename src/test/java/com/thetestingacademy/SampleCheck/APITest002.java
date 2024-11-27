package com.thetestingacademy.SampleCheck;

import io.restassured.RestAssured;

public class APITest002 {
    public static void main(String[] args) {
        // Gherkin Syntax
        // Full url: https://restful-booker.herokuapp.com/booking/:id
        // Base URI: https://restful-booker.herokuapp.com
        //base path :/booking/:id
        // Tc to call GetBooking method
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
