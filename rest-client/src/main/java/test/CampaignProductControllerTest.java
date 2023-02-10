package test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import template.TokenBasedAbstractControllerTest;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

@Slf4j
@SuppressWarnings({"Duplicates"})
public class CampaignProductControllerTest extends TokenBasedAbstractControllerTest {

    public static void main(String[] args) {
        CampaignProductControllerTest invoker = new CampaignProductControllerTest();

        invoker.getAllProductRequest();
    }

    public void getAllProductRequest() {
        String webServiceURL = BASE_URI + "campaigns/search";

        JsonArrayBuilder fields = Json.createArrayBuilder();

        JsonObject searchNameField = Json.createObjectBuilder()
                .add("key", "name")
                .add("value", "new")
                .build();
        fields.add(searchNameField);

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("fromDate", "2020-12-02")
                .add("toDate", "2020-12-20")
                .add("page", "0")
                .add("size", "10")
                .add("search", fields)
                .build();


        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String result = restTemplate.postForObject(webServiceURL, request, String.class);

        log.info("result : " + result);
    }

}
