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
public class PasswordChangeControllerTest extends TokenBasedAbstractControllerTest {

    public static void main(String[] args) {
        PasswordChangeControllerTest invoker = new PasswordChangeControllerTest();

        invoker.passwordChange();
    }

    public void passwordChange() {
        String webServiceURL = BASE_URI + "password/change";

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("oldPassword", "password")
                .add("newPassword", "def1234")
                .build();

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        String result = restTemplate.postForObject(webServiceURL, request, String.class);

        log.info("result : " + result);
    }


}
