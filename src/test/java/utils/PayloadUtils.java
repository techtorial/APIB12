package utils;

public class PayloadUtils {

    public static String getPetPayload() {
        return "{\n" +
                "  \"id\": 6731343,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"hatiko\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"in flight from java code\"\n" +
                "}";
    }


    public static String getSlackPayload(String message) {
        return "{\n" +
                "    \"channel\": \"C03NJHUM474\",\n" +
                "    \"text\": \"" + message + "\"\n" +
                "}";
    }


}
