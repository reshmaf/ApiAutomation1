package com.neev7.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApITest {


    @Test
    public void getUsersTest() {

        RestAssured.baseURI = "https://reqres.in/";

        Response response = given().header("Content-Type", "application/json")
                .param("page", "2")
                .when().get("api/users").then().extract().response();

        System.out.println(response.prettyPrint());

        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.jsonPath().getString("data[0].email"));
    }

    @Test
    public void postUsersTest() {

        RestAssured.baseURI = "https://reqres.in/";

        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response response = given().header("Content-Type", "application/json")
                .and()
                .body(requestBody).log().body() //will print body first
                .when()
                .post("api/users").then().log().all()  //log will print all data
                .extract().response();

    }

    @Test
    public void postUserTestWithSimple() {

        RestAssured.baseURI = "https://reqres.in/";

        JSONObject requestObject = new JSONObject();

        requestObject.put("name", "reshma");
        requestObject.put("job", "designer");

        Response response = given().header("Content-Type", "application/json")
                .and()
                .body(requestObject.toJSONString()).log().body() //will print body first
                .when()
                .post("api/users").then().log().all()  //log will print all data
                .extract().response();

    }

}