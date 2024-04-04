package com.nicolas.cepSpring.controllersTI;

import com.nicolas.cepSpring.enums.Messages;
import com.nicolas.cepSpring.exceptions.ErrorResponse;
import com.nicolas.cepSpring.responses.ResponseCorreios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
    @DisplayName("Busca Cep´s por range")
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
    @Test
    @DisplayName("Busca Cep´s por range mas nenhum foi encontrado")
    void testFindCepByRangeNotFound(){
        String cepInicial = "06413001";
        String cepFinal = "06413002";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        StringBuilder url = new StringBuilder(RESOURCE_BASE_URL);
        url.append("/range");
        url.append(String.format("?cepInicial=%s", cepInicial));
        url.append(String.format("&cepFinal=%s", cepFinal));

        ResponseEntity responseGet = restTemplate.exchange(url.toString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<>() {});

        Assertions.assertNotNull(responseGet);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST
                .toString(), responseGet.getStatusCode().toString());
    }
    @Test
    @DisplayName("Busca Cep´s invalido por range")
    void testFindCepInvalidByRange(){
        String cepInicial = "0641300";
        String cepFinal = "06413002";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        StringBuilder url = new StringBuilder(RESOURCE_BASE_URL);
        url.append("/range");
        url.append(String.format("?cepInicial=%s", cepInicial));
        url.append(String.format("&cepFinal=%s", cepFinal));

        ResponseEntity responseGet = restTemplate.exchange(url.toString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<>() {});

        Assertions.assertNotNull(responseGet);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.toString(), responseGet.getStatusCode().toString());
    }

    @Test
    @DisplayName("Busca de endereço por cep")
    void testFindCep(){
        String cep = "06413000";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        StringBuilder url = new StringBuilder(RESOURCE_BASE_URL);
        url.append("/" + cep);

        ResponseEntity<ResponseCorreios> responseGet = restTemplate.exchange(url.toString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<ResponseCorreios>() {});

        Assertions.assertNotNull(responseGet);
        Assertions.assertEquals(cep, responseGet.getBody().getCep().toString());
    }

    @Test
    @DisplayName("Busca de endereço por cep mas ele não é encontrado")
    void testCepNotFound(){
        String cep = "06413001";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        StringBuilder url = new StringBuilder(RESOURCE_BASE_URL);
        url.append("/" + cep);

        ResponseEntity responseGet = restTemplate.exchange(url.toString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<>() {});

        Assertions.assertNotNull(responseGet);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.toString(), responseGet.getStatusCode().toString());
    }

    @Test
    @DisplayName("Busca de endereço por cep mas o cep é invalido")
    void testFindCepInvalid(){
        String cep = "0641300";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        StringBuilder url = new StringBuilder(RESOURCE_BASE_URL);
        url.append("/" + cep);

        ResponseEntity responseGet = restTemplate.exchange(url.toString(), HttpMethod.GET, requestEntity, new ParameterizedTypeReference<>() {});

        Assertions.assertNotNull(responseGet);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.toString(), responseGet.getStatusCode().toString());
    }
}
