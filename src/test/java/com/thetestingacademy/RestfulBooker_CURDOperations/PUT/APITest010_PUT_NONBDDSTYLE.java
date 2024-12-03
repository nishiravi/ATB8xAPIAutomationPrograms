package com.thetestingacademy.RestfulBooker_CURDOperations.PUT;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest010_PUT_NONBDDSTYLE {

    RequestSpecification requestspecification= RestAssured.given();
    @Description(" Verify the restful Booker PUT Request")
    @Test
    public void test_updateBooking()
    {
        String token="33b1c0193be3a2d";
        int bookingID=13062;
        String payloadPut="{\n" +
                "    \"firstname\" : \"TestRestAssuredUSerUpdate\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        requestspecification.baseUri("https://restful-booker.herokuapp.com");
        requestspecification.basePath("/booking/"+bookingID);
        requestspecification.cookie(token,token);
        requestspecification.contentType(ContentType.JSON);
        requestspecification.auth().preemptive().basic("admin","password123");
        requestspecification.body(payloadPut).log().all();
        Response response=requestspecification.when().put();
        ValidatableResponse valiadteresponse=response.then().log().all();
        valiadteresponse.statusCode(200);


    }

}
