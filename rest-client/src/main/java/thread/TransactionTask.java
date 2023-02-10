package thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
public class TransactionTask {

    public static final String BASE_URI = "http://localhost:10102/";

    public static void transaction(long offerTransactionId){

        String webServiceURL = BASE_URI + "redeem/accept";

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        jsonArrayBuilder.add(offerTransactionId);

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("offerTransactions", jsonArrayBuilder)
                .build();

        log.info(jsonObject.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(webServiceURL, request, String.class);

        log.info("result : " + result);
    }
}
