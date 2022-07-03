package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.PokemonPojo;

public class PojoDeserialization {


    @Test
    public void pokemonTest() {
        Response response = RestAssured.given()
                .accept("application/json")
                .when().get("https://pokeapi.co/api/v2/pokemon")
                .then().statusCode(200).extract().response();

        PokemonPojo parsedResp = response.as(PokemonPojo.class);

        /**
         * .setCount(1154)
         * .setNext("https://pokeapi.co/api/v2/pokemon?offset=20&limit=20")
         *
         */


        Assert.assertEquals(1154, parsedResp.getCount());







    }


}
