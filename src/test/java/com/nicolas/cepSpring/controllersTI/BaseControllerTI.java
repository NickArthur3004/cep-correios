package com.nicolas.cepSpring.controllersTI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

public class BaseControllerTI {

    @Autowired
    protected TestRestTemplate restTemplate;

    protected HttpEntity<String> requestEntity;
}
