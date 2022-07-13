package step_defs;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class CovidStepDefs {

    Response response;

    @When("user sends GET request to Covid api")
    public void userSendsGETRequestToCovidApi() {

      response = RestAssured.given().accept(JSON)
                .when().get("https://corona.lmao.ninja/v2/states");

    }

    @Then("user get information about all states")
    public void userGetInformationAboutAllStates() {

        List<Map<String, Object>> parsedResponse =
                response.as(new TypeRef<List<Map<String, Object>>>() {
                });
        Assert.assertTrue(parsedResponse.size() == 50);

    }

    @Then("status is {int}")
    public void statusIs(int expectedStatusCode) {

        int actualStatusCode = response.statusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

    }

}
