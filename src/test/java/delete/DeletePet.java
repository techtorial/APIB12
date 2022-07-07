package delete;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayloadUtils;

public class DeletePet {

    @Test
    public void deletePetTest() {

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        /**
         * 1. Create a pet => https://petstore.swagger.io/v2/pet
         * 2. Delete a pet => https://petstore.swagger.io/v2/pet/6731343
         */
        //Create a pet


        System.out.println("Create a pet");
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(PayloadUtils.getPetPayload())
                .when().post()
                .then().statusCode(200)
                .extract().response();

        PetPojo parsedResponse = response.as(PetPojo.class);
        int petId = parsedResponse.getId();


        System.out.println("Delete the pet");
        //Delete a pet
        RestAssured.given().accept("application/json")
                .when().delete(String.valueOf(petId))
                .then().statusCode(200)
                .and()
                .body("message",
                        Matchers.equalTo(String.valueOf(petId))).log().all();

        System.out.println("Get the pet");
        //Try to get a pet and validate that it does not exist
        RestAssured.given().accept("application/json")
                .when().get(String.valueOf(petId))
                .then().statusCode(404)
                .and()
                .body("message", Matchers.is("Pet not found")).log().all();





    }


}
