package com.thetestingacademy.PayloadManagment.POJOClass_Easyway;

import com.thetestingacademy.PayloadManagment.POJOClass_Easyway.Booking;
import com.thetestingacademy.PayloadManagment.POJOClass_Easyway.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class CreateBookingUsingPOJOClass {


        @Test
        public void createBooking()
        {
            RequestSpecification requestSpecification = RestAssured.given();
            ValidatableResponse validatableResponse;
            Response response;
            Booking booking=new Booking();
            booking.setFirstname("nishi");
            booking.setLastname("Ravi");
            booking.setTotalprice(111);
            booking.setDepositpaid(true);
            Bookingdates bookingdates= new Bookingdates();
            bookingdates.setCheckin("2018-01-01");
            bookingdates.setCheckout("2019-01-01");
            booking.setBookingdates(bookingdates); // pass the object of BookingDate class
            booking.setAdditionalneeds("breafast");

            requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
            requestSpecification.basePath("booking");
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.body(booking).log().all();

            response = requestSpecification.when().post();

            Integer bookingId = response.then().extract().path("bookingid");

            // Get Validatable response to perform validation
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);
            System.out.println("Your Booking Id is -> " +  bookingId);
        }
}
