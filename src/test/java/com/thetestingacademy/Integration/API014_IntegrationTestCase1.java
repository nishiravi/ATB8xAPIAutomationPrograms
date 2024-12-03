package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class API014_IntegrationTestCase1 {

    //  Create a Token
    // Create a Booking
    //  Perform  a PUT request
    //  Verify that PUT is success by GET Request
    // Delete the ID
    // Verify it doesn't exist GET Request

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    String bookingID;

    public String getToken() {
        requestSpecification = RestAssured.given();
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
                "    \"lastname\" : \"Brown\",\n" +
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
    public void test_updateBooking() {
        String token = getToken();
        String bookingID = getBookingID();
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
        validatableResponse.statusCode(200);


    }

    @Test(priority = 2)
    public void GetBooking() {
        System.out.println(bookingID);
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingID);
        response = requestSpecification.when().log().all().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        String firstname = response.jsonPath().getString("firstname");
        Assert.assertEquals(firstname, "Shriyan");
    }

    @Test(priority = 3)
    public void test_DeleteBooking() {
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

    @Test(priority = 4)
    public void test_after_delete_request_get() {
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/" + bookingID);
        response = requestSpecification.when().log().all().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

    }
}
