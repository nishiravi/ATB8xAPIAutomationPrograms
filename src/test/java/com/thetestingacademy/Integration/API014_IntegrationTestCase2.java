package com.thetestingacademy.Integration;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class API014_IntegrationTestCase2 {
    RequestSpecification requestSpecification = RestAssured.given();
    Response response;
    ValidatableResponse validatableResponse;
    String bookingID;

    // Create booking
    // Verify the GET Request
    public String getBookingID() {
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        String payloadCreate = "{\n" +
                "    \"firstname\" : \"Nishi\",\n" +
                "    \"lastname\" : \"Sangeeth\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        requestSpecification.body(payloadCreate);
        response = requestSpecification.when().log().all().post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        bookingID = response.jsonPath().getString("bookingid");
        return bookingID;
    }

    @Description(" Verify the GETBookingID Request")
    @Test
    public void GetBooking() {
        bookingID = getBookingID();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingID);
        response = requestSpecification.when().log().all().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

}
