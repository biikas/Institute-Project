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
public class CampaignControllerTest extends TokenBasedAbstractControllerTest {

    public static void main(String[] args) {
        CampaignControllerTest invoker = new CampaignControllerTest();

        invoker.getTransactionList();
    }



    public void getTransactionList() {
        String webServiceURL = BASE_URI + "campaigns/transaction/list";
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> result = restTemplate.exchange(webServiceURL, HttpMethod.GET, request, String.class);
        log.info("result : " + result.getBody());
    }
}
