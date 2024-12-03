package com.thetestingacademy.SAMPLECURDOperations.GET;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting006_NonBDDStyle_Get {

    static RequestSpecification r = RestAssured.given();
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC1-test_NonBDDStyle_Get-PositiveTc")
    @Test
    public void test_NonBDDStyle_Get() {
        String pincode = "20171";

        r.baseUri("https://api.zippopotam.us");
        r.basePath("/us/" + pincode);
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }
    @Severity(SeverityLevel.NORMAL)
    @Description("TC1-test_NonBDDStyle_Get-NegativeTc")
    @Test
    public void test_NonBDDStyle_Get_Negative() {
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/us/0");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }
}
