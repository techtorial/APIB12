package post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import pojo.PetPojo;

import java.io.File;
import java.io.IOException;

public class Serialization {

    @Test
    public void serializationTest() throws IOException {
        PetPojo pet = new PetPojo();

        pet.setId(6738931);
        pet.setName("Pet from java");
        pet.setStatus("serialized");

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/java/post/pet.json");
        objectMapper.writeValue(file, pet);


    }
}
