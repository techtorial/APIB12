package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pokemon {

    @Test
    public void pokemonTest(){
        //https://pokeapi.co/api/v2/pokemon

       Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get("https://pokeapi.co/api/v2/pokemon")
                .then().statusCode(200).extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        //count
        int actualCount = (int) parsedResponse.get("count");
        Assert.assertEquals(1154, actualCount);

        //previous
        Assert.assertNull(parsedResponse.get("previous"));


       List<Map<String, String>> resultsList =
               (List<Map<String, String>>) parsedResponse.get("results");

       List<String> pokemonNamesList = new ArrayList<>();

       for (Map<String, String> tempMap: resultsList){
           pokemonNamesList.add(tempMap.get("name"));
       }

       System.out.println(pokemonNamesList);
       Assert.assertFalse(pokemonNamesList.isEmpty());








    }






}
