package template;

import config.RestTemplateResponseErrorHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class AbstractControllerTest {

    //    public static final String BASE_URI = "https://bankxp.f1soft.com.np/gprs/";
    public static final String BASE_URI = "http://localhost:10102/";
    //    public static final String BASE_URI = "http://10.13.194.173:7080/";
    public static final String CUSTOMIZATION = "http://localhost:8090/bank-web";

    protected RestTemplate restTemplate;
    protected HttpHeaders headers;

    public AbstractControllerTest() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(getClientHttpRequestFactory());

        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());

        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }

    protected ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 2000; //2 secs
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(20 * 1000); //60 secs
        return clientHttpRequestFactory;
    }
}
