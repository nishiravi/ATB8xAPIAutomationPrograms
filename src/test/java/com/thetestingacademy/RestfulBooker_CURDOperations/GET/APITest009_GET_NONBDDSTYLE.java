package com.thetestingacademy.RestfulBooker_CURDOperations.GET;


import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest009_GET_NONBDDSTYLE {

    RequestSpecification requestspecification= RestAssured.given();
    Response response;
    ValidatableResponse validatableResponse;
    @Description(" verify restful booker GET Request")
    @Test
    public void GetBookingIds()
    {
        requestspecification.baseUri("https://restful-booker.herokuapp.com");
        requestspecification.basePath("/booking");
        response=requestspecification.when().log().all().get();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
    }
    @Test
    public void GetBooking()
    {
        int bookingID=4501;
        requestspecification.baseUri("https://restful-booker.herokuapp.com");
        requestspecification.basePath("/booking/"+bookingID);
        response=requestspecification.when().log().all().get();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
    }
}
