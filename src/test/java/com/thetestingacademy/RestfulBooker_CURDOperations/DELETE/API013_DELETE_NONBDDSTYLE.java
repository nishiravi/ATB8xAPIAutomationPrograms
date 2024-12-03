package com.thetestingacademy.RestfulBooker_CURDOperations.DELETE;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API013_DELETE_NONBDDSTYLE {
    RequestSpecification requestspecification= RestAssured.given();
    @Description(" Verify the restful Booker PATCH Request")
    @Test
    public void test_DeleteBooking()
    {
        String token="0b2666c68841a5b";
        int bookingID=6198;
        requestspecification.baseUri("https://restful-booker.herokuapp.com");
        requestspecification.basePath("/booking/"+bookingID);
        requestspecification.cookie(token,token);
        requestspecification.contentType(ContentType.JSON);
        requestspecification.auth().preemptive().basic("admin","password123");
        requestspecification.log().all();
        Response response=requestspecification.when().delete();
        ValidatableResponse valiadteresponse=response.then().log().all();
        valiadteresponse.statusCode(201);


    }
}
