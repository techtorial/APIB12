package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CatFactsPojo;

public class CatFacts {


    @Test
    public void catFactsTest() {
        RestAssured.baseURI = "https://catfact.ninja";
        RestAssured.basePath = "facts";

        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).extract().response();

        CatFactsPojo parsedCatResponse = response.as(CatFactsPojo.class);

        int perPage = parsedCatResponse.getPer_page();
        int totalFactsPerPage = parsedCatResponse.getData().size();

        Assert.assertEquals(perPage, totalFactsPerPage);
    }


}
