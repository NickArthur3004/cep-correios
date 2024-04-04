package com.nicolas.cepSpring.controllers;


import com.nicolas.cepSpring.exceptions.ErrorResponse;
import com.nicolas.cepSpring.responses.ResponseCorreios;
import com.nicolas.cepSpring.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cep")
public class CepController {

    @Autowired
    private CepService service;

    @GetMapping(value = "/range")
    ResponseEntity<?> findCepsByRange(@RequestParam String cepInicial, @RequestParam String cepFinal) throws Exception {
        ResponseEntity responses = service.findZipCodesByRange(cepInicial, cepFinal);
        return responses;
    }

    @GetMapping(value = "/{cep}")
    ResponseEntity<?> findByCep(@PathVariable(value = "cep") String cep) throws Exception {
        ResponseEntity<?> response = service.findZipCode(cep);
        return response;
    }
}
