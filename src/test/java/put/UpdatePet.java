package put;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.Assert.assertEquals;

public class UpdatePet {

    @Test
    public void updatePetTest() {


        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet";

        int petId = 9873476;
        String petName = "Buzzy";
        String petStatus = "noisy pet";


        PetPojo pet = new PetPojo();
        pet.setId(petId);
        pet.setName(petName);
        pet.setStatus(petStatus);


        Response response = given()
                .accept("application/json")
                .contentType(JSON)
                .body(pet)
                .when().put()
                .then().statusCode(200).extract().response();

        Map<String, Object> parsedPetResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

//DRY - Do not Repeat Yourself
        int actualId = (int) parsedPetResponse.get("id");
        String actualName = parsedPetResponse.get("name").toString();
        String actualStatus = parsedPetResponse.get("status").toString();

        assertEquals(petId, actualId);
        assertEquals(petName, actualName);
        assertEquals(petStatus, actualStatus);


    }





    @Test
    public void allCallsTest(){
        //Create a pet
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet";

        String expectedName = "Snoopy";
        int expectedId = 4387384;
        String expectedStatus = "gone fishing";

        PetPojo pet = new PetPojo();
        pet.setName(expectedName);
        pet.setId(expectedId);
        pet.setStatus(expectedStatus);

        Response response = given().contentType(JSON).accept(JSON).body(pet)
                .when().post()
                .then().statusCode(200)
                .extract().response();

        PetPojo parsedPostResponse = response.as(PetPojo.class);

        assertEquals(pet.getName(), parsedPostResponse.getName());
        assertEquals(pet.getId(), parsedPostResponse.getId());
        assertEquals(pet.getStatus(), parsedPostResponse.getStatus());

        //GET request
        Response getResponse = given().accept(JSON).when().get(String.valueOf(pet.getId()))
                .then().statusCode(200).log().all().extract().response();

        PetPojo parsedGetResponse = getResponse.as(PetPojo.class);
        assertEquals(pet.getName(), parsedGetResponse.getName());
        assertEquals(pet.getId(), parsedGetResponse.getId());
        assertEquals(pet.getStatus(), parsedGetResponse.getStatus());

        //PUT request
        pet.setName("Buzzy");
        Response putResponse = given().accept(JSON).contentType(JSON).body(pet).when().put()
                .then().statusCode(200).body("name", Matchers.equalTo(pet.getName())).log().all().extract().response();

        //GET request
        getResponse = given().accept(JSON).when().get(String.valueOf(pet.getId()))
                .then().statusCode(200).log().all().extract().response();

        parsedGetResponse = getResponse.as(PetPojo.class);
        assertEquals(pet.getName(), parsedGetResponse.getName());
        assertEquals(pet.getId(), parsedGetResponse.getId());
        assertEquals(pet.getStatus(), parsedGetResponse.getStatus());


        //DELETE request
       Response deleteResponse =  given().accept(JSON).when().delete(String.valueOf(pet.getId()))
                .then().statusCode(200).body("message", Matchers.is(String.valueOf(pet.getId()))).extract().response();

        PetPojo parsedDeleteResponse = deleteResponse.as(PetPojo.class);
        assertEquals(String.valueOf(pet.getId()), parsedDeleteResponse.getMessage());

        //GET request
        given().accept(JSON).when().get(String.valueOf(pet.getId()))
                .then().statusCode(404).log().all();

    }








}
