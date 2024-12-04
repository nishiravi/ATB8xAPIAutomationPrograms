package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class API017_IntegrationTestCase4 {
    //  Create a Token
    // Create a Booking
    // Delete the ID
    // Try to Update the deleted ID by PUT request

    RequestSpecification requestSpecification = RestAssured.given();
    ;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    String bookingID;

    public String getToken() {
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.log().all().body(payload);
        response = requestSpecification.when().log().all().post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        token = response.jsonPath().getString("token");
        return token;
    }

    public String getBookingID() {
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        String payloadCreate = "{\n" +
                "    \"firstname\" : \"Nishi\",\n" +
                "    \"lastname\" : \"BrownTest\",\n" +
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

    @Test(priority = 1)
    public void test_DeleteBooking() {
        String token = getToken();
        String bookingID = getBookingID();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingID);
        requestSpecification.cookie(token, token);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.auth().preemptive().basic("admin", "password123");
        requestSpecification.log().all();
        response = requestSpecification.when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test(priority = 2)
    public void test_updateBooking() {
        String payloadPut = "{\n" +
                "    \"firstname\" : \"Shriyan\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingID);
        requestSpecification.cookie(token, token);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.auth().preemptive().basic("admin", "password123");
        requestSpecification.body(payloadPut).log().all();
        response = requestSpecification.when().put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(405);
        String putresponse = response.then().log().all().toString();
        System.out.println(putresponse);


    }


}
