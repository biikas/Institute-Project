package template;

import config.JacksonUtil;
import config.RestTemplateResponseErrorHandler;
import config.TokenDTO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonObject;


@Slf4j
@SuppressWarnings({"Duplicates"})
@Getter
public class TokenBasedAbstractControllerTest extends AbstractControllerTest {
    protected String token;

    public TokenBasedAbstractControllerTest() {
//        this.doLogin();
        this.webLogin();
    }



    public void webLogin() {
        try {
            String webServiceURL = BASE_URI + "auth/login";


            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("username", "nitesh")
                    .add("password", "password")
                    .build();

            HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);

            restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

            HttpEntity<String> response = restTemplate.exchange(webServiceURL, HttpMethod.POST, request, String.class);

            String result = response.getBody();

            log.info("login result : " + result);

            HttpHeaders headers = response.getHeaders();

            this.token = headers.get(HttpHeaders.AUTHORIZATION).get(0);

            this.headers.set(HttpHeaders.AUTHORIZATION, token);

            log.info("Token : " + this.token);

        } catch (HttpClientErrorException e) {
            log.error("Exception : ", e);
            int statusCode = e.getStatusCode().value();
            log.error("status code : " + statusCode);
            String responseString = e.getResponseBodyAsString();
            log.error(responseString);
        } catch (RestClientException e) {
            log.error("Exception : " + e.getMessage());
        } catch (Exception e) {
            log.error("Exception : " + e.getMessage());
        }
    }

}
