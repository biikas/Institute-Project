package test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import template.TokenBasedAbstractControllerTest;

import javax.json.Json;
import javax.json.JsonObject;

@Slf4j
@SuppressWarnings({"Duplicates"})
public class UserProfileControllerTest extends TokenBasedAbstractControllerTest {

    public static void main(String[] args) {
        UserProfileControllerTest invoker = new UserProfileControllerTest();

//        invoker.userProfile();
        invoker.forgetPassword();
    }

    public void userProfile() {
        String webServiceURL = BASE_URI + "users/token";
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(webServiceURL, HttpMethod.GET, request, String.class);
        log.info("result : " + result.getBody());
    }

    public void forgetPassword() {
        String webServiceURL = BASE_URI + "users/password/change";

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("userId", "6")
                .add("newPassword", "aaa1234")
                .build();

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String result = restTemplate.postForObject(webServiceURL, request, String.class);

        log.info("result : " + result);
    }

}
