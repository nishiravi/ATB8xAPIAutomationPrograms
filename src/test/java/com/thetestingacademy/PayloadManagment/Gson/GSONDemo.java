package com.thetestingacademy.PayloadManagment.Gson;

import com.google.gson.Gson;
import com.thetestingacademy.PayloadManagment.Gson.Booking;
import com.thetestingacademy.PayloadManagment.Gson.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GSONDemo {

    // step 1: convert object to JSON-serialization
    // Step 2 : use the JSON into the request
    // Step 3:verify the response/DESERIALIZATION-
    // used when response is huge-create a response class
    // Example BookingResponse;
    RequestSpecification requestSpecification = RestAssured.given();
    ValidatableResponse validatableResponse;
    Response response;

    @Test
    public void testPositive() {
        Booking booking = new Booking();
        booking.setFirstname("nishi");
        booking.setLastname("Ravi");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates); // pass the object of BookingDate class
        booking.setAdditionalneeds("breafast");
        System.out.println(booking);// this is an object
        //Step1:
        // convert object to JSON-SERIALIZATION
        Gson gson=new Gson();
        String jsonStringBooking=gson.toJson(booking);
        System.out.println(jsonStringBooking);
        // Step 2
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonStringBooking).log().all();// step 2 using the JSONString
        response = requestSpecification.when().post();
        Integer bookingId = response.then().extract().path("bookingid");

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        System.out.println("Your Booking Id is -> " + bookingId);
        // Step3: DESERIALIZATION
          BookingResponse bookingResponse=gson.fromJson(jsonStringBooking,BookingResponse.class);
          // Step 4:response validation
        assertThat(bookingResponse.getBookingid()).isNotZero().isPositive();
        assertThat(bookingResponse.getBooking().getFirstname()).isAlphabetic().isEqualTo("nishi");
    }
}
