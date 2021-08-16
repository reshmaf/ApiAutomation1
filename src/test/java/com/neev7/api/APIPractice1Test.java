package com.neev7.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class APIPractice1Test {
    @Test
    public void getBookingIds() {
        RestAssured.baseURI="https://restful-booker.herokuapp.com/";

        Response response=given().header("Content-Type","application/json")
                .when().get("booking").then().extract().response();
        response.prettyPrint();

        Assert.assertEquals(200, response.getStatusCode());
    }

   /* @Test
    public void getBookingIdsByFilterName() {
        RestAssured.baseURI="https://restful-booker.herokuapp.com/";

        Response response=given().header("Content-Type","application/json")
                .param("firstname","sally").and().param("lastname","brown")
                .when().get("booking").then().extract().response();
        response.prettyPrint();

       // Assert.assertEquals(200, response.getStatusCode());
    } */

    @Test
    public void getBooking() {
        RestAssured.baseURI="https://restful-booker.herokuapp.com/";

        Response response=given().header("Content-Type","application/json")
                .when().post("booking/1").then().extract().response();
        response.prettyPrint();
    }

    @Test
    public void createBooking() {
        RestAssured.baseURI="https://restful-booker.herokuapp.com/";

       /* String requestBody="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}"; */

        JSONObject requestObj=new JSONObject();
        JSONObject bookingObj=new JSONObject();

        requestObj.put("firstname","Jim");
        requestObj.put("lastname","Brown");
        requestObj.put("totalprice","111");
        requestObj.put("depositpaid","true");
        bookingObj.put("checkin","2018-01-01");
        bookingObj.put("checkout","2019-01-01");
        requestObj.put("bookingdates",bookingObj);
        requestObj.put("additionalneeds","Breakfast");

        Response response =given().header("Content-Type","application/json").and().header("Accept","application/json")
                .and()
                .body(requestObj.toJSONString())
                .when()
                .post("/booking").then().log().all().extract().response();
        System.out.println(response.getStatusCode());
    }

}

