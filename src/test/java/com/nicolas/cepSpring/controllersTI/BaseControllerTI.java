package com.nicolas.cepSpring.controllersTI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class BaseControllerTI {

    @Autowired
    protected TestRestTemplate restTemplate;
}
