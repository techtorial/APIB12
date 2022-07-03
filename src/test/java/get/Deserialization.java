package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class Deserialization {

    @Test
    public void deserializationTest(){

        //https://petstore.swagger.io/v2/pet/10567
       Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get("https://petstore.swagger.io/v2/pet/10567")
                .then().statusCode(200).extract().response();


       //deserialization
        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        /**
         * deserializedResponse.put("id", 10567);
         * deserializedResponse.put("category", ....);
         */

//        System.out.println(deserializedResponse);

        //validating pet id
        Assert.assertEquals(10567, deserializedResponse.get("id"));

        //validating pet name
        String actualPetName = String.valueOf(deserializedResponse.get("name"));
        Assert.assertEquals("hatiko", actualPetName);


        Map<String, Object> categoryMap = (Map<String, Object>) deserializedResponse.get("category");
        Object categoryMap1 = deserializedResponse.get("category");

        System.out.println("Category map: "+categoryMap);









    }







}
