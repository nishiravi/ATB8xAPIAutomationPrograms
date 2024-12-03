package com.thetestingacademy.SAMPLECURDOperations.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting008 {

    RequestSpecification r= RestAssured.given();


    @Description("Verify the Token generated successfully")
    @Test
    public void test_CreateToken()
    {
        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.log().all().body(payload);
        Response response=r.when().log().all().post();
        response.then().log().all().statusCode(200);
    }
}
