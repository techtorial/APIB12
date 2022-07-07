package post;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.mapper.factory.DefaultJackson2ObjectMapperFactory;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.SlackPojo;
import utils.PayloadUtils;

import java.lang.reflect.Type;

public class Slack {


    @Test
    public void sendMessageTest() {
        //https://slack.com/api/chat.postMessage

        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                new ObjectMapperConfig().jackson2ObjectMapperFactory(new DefaultJackson2ObjectMapperFactory(){
                    @Override
                    public ObjectMapper create(Type cls, String charset){
                        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        return objectMapper;
                    }
                }));


        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization",
                        "Bearer ")
                .body(PayloadUtils.getSlackPayload())
                .when().post()
                .then().statusCode(200).extract().response();


        SlackPojo parsedResponse = response.as(SlackPojo.class);
        Assert.assertTrue(parsedResponse.isOk());


    }
}
