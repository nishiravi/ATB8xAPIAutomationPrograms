package com.thetestingacademy.Assertions;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class API019_TestNGAssertion {
    RequestSpecification requestSpecification = RestAssured.given();
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void createBookingID() {
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        String payloadCreate = "{\n" +
                "    \"firstname\" : \"Nishi\",\n" +
                "    \"lastname\" : \"Test\",\n" +
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
        int bookingID = response.then().extract().path("bookingid");
        String firstname = response.then().extract().path("booking.firstname");
        String lastname = response.then().extract().path("booking.lastname");
        Assert.assertNotNull(bookingID);
        // Hard assert
        Assert.assertEquals(firstname, "Nishi");
        // Soft Assert
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(lastname, "Test");
        sa.assertTrue(false); // will fail because assertTrue expects true
        sa.assertAll();
    }
}
