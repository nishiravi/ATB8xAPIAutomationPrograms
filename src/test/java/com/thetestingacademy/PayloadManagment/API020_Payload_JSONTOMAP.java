package com.thetestingacademy.PayloadManagment;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class API020_Payload_JSONTOMAP {
   @Test
    public void trestPostReq() {
        RequestSpecification requestSpecification = RestAssured.given();
        ValidatableResponse validatableResponse;
       Response response;

        //convert JSON to HashMAP
        Map<String, Object> jsonBodyUsingMap = new LinkedHashMap<>();
        jsonBodyUsingMap.put("firstname", "Jim");
        jsonBodyUsingMap.put("lastname", "Brown");
        jsonBodyUsingMap.put("totalprice", "111");
        jsonBodyUsingMap.put("depositpaid", "true");
        jsonBodyUsingMap.put("additionalneeds", "Breakfast");

        Map<String, Object> bookingdatesMap = new LinkedHashMap<>();
        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");
        // add map into another map
        jsonBodyUsingMap.put("bookingdates",bookingdatesMap);
        System.out.println(jsonBodyUsingMap);

        // convert MAp to JSON format-by using GSON liobraray , add GSON dependency to POM.XML
       requestSpecification.baseUri("https://restful-booker.herokuapp.com");
       requestSpecification.basePath("/booking");
       requestSpecification.contentType(ContentType.JSON);
       requestSpecification.body(jsonBodyUsingMap);
       response = requestSpecification.when().log().all().post();
       validatableResponse = response.then().log().all();
       validatableResponse.statusCode(200);

    }
}
