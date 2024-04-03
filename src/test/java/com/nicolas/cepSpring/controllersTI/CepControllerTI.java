package com.nicolas.cepSpring.controllersTI;

import com.nicolas.cepSpring.responses.ResponseCorreios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CepControllerTI extends BaseControllerTI{

    String RESOURCE_BASE_URL = "/cep";

    @Test
    void testFindCepByRange(){
            String cepInicial = "06413000";
            String cepFinal = "06413999";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            StringBuilder url = new StringBuilder(RESOURCE_BASE_URL);
            url.append("/range");
            url.append(String.format("?cepInicial=%s", cepInicial));
            url.append(String.format("&cepFinal=%s", cepFinal));

            ResponseEntity<List<ResponseCorreios>> responseGet = restTemplate.exchange(url.toString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<ResponseCorreios>>() {});

            Assertions.assertNotNull(responseGet);
            Assertions.assertEquals(45, responseGet.getBody().size());
    }
}
