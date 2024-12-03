package com.thetestingacademy.RestfulBooker_CURDOperations.PATCH;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API012_PATCH_NONBDDSTYLE {
    RequestSpecification requestspecification= RestAssured.given();
    @Description(" Verify the restful Booker PATCH Request")
    @Test
    public void test_PartialUpdateBooking()
    {
        String token="0b2666c68841a5b";
        int bookingID=2865;
        String payloadPatch="{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\"\n" +
                "}";
        requestspecification.baseUri("https://restful-booker.herokuapp.com");
        requestspecification.basePath("/booking/"+bookingID);
        requestspecification.cookie(token,token);
        requestspecification.contentType(ContentType.JSON);
        requestspecification.auth().preemptive().basic("admin","password123");
        requestspecification.body(payloadPatch).log().all();
        Response response=requestspecification.when().patch();
        ValidatableResponse valiadteresponse=response.then().log().all();
        valiadteresponse.statusCode(200);


    }
}
