package test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import template.TokenBasedAbstractControllerTest;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 * @author <krishna.pandey@college.com>
 */
@Slf4j
public class GiftCardControllerTest extends TokenBasedAbstractControllerTest {

    public static void main(String[] args) {
        GiftCardControllerTest invoker = new GiftCardControllerTest();

//        invoker.createUser();
        invoker.modifyUser();
//        invoker.getAllUser();
//        invoker.searchUser();
//        invoker.geUserById();
    }

    private void searchUser() {
        String webServiceURL = BASE_URI + "gift-card/search";

        JsonArrayBuilder fields = Json.createArrayBuilder();

//        JsonObject searchNameField = Json.createObjectBuilder()
//                .add("key", "name")
//                .add("value", "asdfsadfa")
//                .build();
//        fields.add(searchNameField);

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("page", "0")
                .add("size", "10")
                .add("search", fields)
                .build();


        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String result = restTemplate.postForObject(webServiceURL, request, String.class);

        log.info("result : " + result);
    }

    public void createUser() {
        String webServiceURL = BASE_URI + "gift-card/create";

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("name", "Test")
                .add("code", "test1")
                .add("giftCardProviderId", "2")
                .build();

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String result = restTemplate.postForObject(webServiceURL, request, String.class);

        log.info("result : " + result);
    }

    public void modifyUser() {
        String webServiceURL = BASE_URI + "gift-card/modify";

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("id", 80)
                .add("name", "test modified")
                .add("code", "test1")
                .add("giftCardProviderId", 1)
                .build();

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String result = restTemplate.postForObject(webServiceURL, request, String.class);

        log.info("result : " + result);
    }

    public void getAllUser() {
        String webServiceURL = BASE_URI + "gift-card/list";
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(webServiceURL, HttpMethod.GET, request, String.class);
        log.info("result : " + result.getBody());
    }

    public void geUserById() {
        String webServiceURL = BASE_URI + "gift-card/2";
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(webServiceURL, HttpMethod.GET, request, String.class);
        log.info("result : " + result.getBody());
    }
}
