package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class Deserialization {

    @Test
    public void deserializationTest() {

        //https://petstore.swagger.io/v2/pet/10567
        Response response = given()
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

        System.out.println("Category map: " + categoryMap);

    }

    @Test
    public void advDeserializationTest() {
        //1. TypeRef
        //2. POJO
        //3. Json Traversing
        //4. JsonPath

        //https://swapi.dev/api/people

        baseURI = "https://swapi.dev/api";
        basePath = "people";

        Response response = given().accept(JSON)
                .when().get()
                .then().statusCode(200).extract().response();

        JsonPath deserializedResponse = response.jsonPath();

        String nextValue = deserializedResponse.getString("next");
        System.out.println(nextValue);
        Assert.assertEquals(baseURI + "/" + basePath + "/?page=2", nextValue);

        List<Object> resultValue = deserializedResponse.getList("results");
//        System.out.println(resultValue);

        List<Map<String, Object>> result = deserializedResponse.getList("results");
        System.out.println(result);

        Map<String, Object> firstObjectFromList =
                deserializedResponse.getMap("results[0]");
        System.out.println(firstObjectFromList);


    }


}
