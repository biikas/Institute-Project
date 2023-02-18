package test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import template.TokenBasedAbstractControllerTest;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

@Slf4j
@SuppressWarnings({"Duplicates"})
public class CreateUserControllerTest extends TokenBasedAbstractControllerTest {

    public static void main(String[] args) {
        CreateUserControllerTest invoker = new CreateUserControllerTest();

//        invoker.createUser();
//        invoker.modifyUser();
//        invoker.getAllUser();
        invoker.searchUser();
//        invoker.geUserById();
    }

    private void searchUser() {
        String webServiceURL = BASE_URI + "users/search";

        JsonArrayBuilder fields = Json.createArrayBuilder();

        JsonObject searchNameField = Json.createObjectBuilder()
                .add("key", "name")
                .add("value", "asdfsadfa")
                .build();
        fields.add(searchNameField);

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
        String webServiceURL = BASE_URI + "users";

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("userName", "admin9")
                .add("password", "abc1234")
                .add("name", "Test")
                .add("emailAddress", "test@college.com")
                .build();

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String result = restTemplate.postForObject(webServiceURL, request, String.class);

        log.info("result : " + result);
    }

    public void modifyUser() {
        String webServiceURL = BASE_URI + "users/modify/2";

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("name", "Shreetika")
                .add("emailAddress", "shreetika.panta@college.com")
                .add("mobileNumber", "9800000000")
                .build();

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String result = restTemplate.postForObject(webServiceURL, request, String.class);

        log.info("result : " + result);
    }

    public void getAllUser() {
        String webServiceURL = BASE_URI + "users/list";
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(webServiceURL, HttpMethod.GET, request, String.class);
        log.info("result : " + result.getBody());
    }

    public void geUserById() {
        String webServiceURL = BASE_URI + "users/2";
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(webServiceURL, HttpMethod.GET, request, String.class);
        log.info("result : " + result.getBody());
    }
}
