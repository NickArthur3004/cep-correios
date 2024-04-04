package com.nicolas.cepSpring.services;


import com.google.gson.*;
import com.nicolas.cepSpring.enums.Messages;
import com.nicolas.cepSpring.exceptions.ErrorResponse;
import com.nicolas.cepSpring.exceptions.ZipCodeInvalidException;
import com.nicolas.cepSpring.exceptions.ZipCodeNotFoundException;
import com.nicolas.cepSpring.models.Util;
import com.nicolas.cepSpring.responses.ResponseCorreios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Service
public class CepService {
    private String token = "Bearer " + "Token de acesso a api correios";
    static String webService = "Url da api que deseja usar";

    public ResponseEntity<?> findZipCode(String cep) throws Exception {
        try {
            validateCep(cep);

            cep = formatCep(cep);

            String pathUrl = webService + "/" + cep;


            BufferedReader response = chamadaEndpoint(pathUrl);

            String jsonEmString = Util.converteJsonEmString(response);

            Gson gson = new Gson();
            ResponseCorreios responseCorreios = gson.fromJson(jsonEmString, ResponseCorreios.class);

            return ResponseEntity.ok().body(responseCorreios);

        }catch (ZipCodeInvalidException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getStatus().toString());
            return ResponseEntity.status(e.getStatus()).body(errorResponse);
        }catch (ZipCodeNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getStatus().toString());
            return ResponseEntity.status(e.getStatus()).body(errorResponse);
        }
    }

    public ResponseEntity<?> findZipCodesByRange(String cepInicial, String cepFinal) throws RuntimeException, Exception {
        try {
            validateCep(cepInicial);
            validateCep(cepFinal);

            String pathUrl = webService + "?" +
                    "cepInicial=" + cepInicial +
                    "&cepFinal=" + cepFinal +
                    "&page=" + 0 +
                    "&size=" + 50;
            Gson gson = new Gson();
            List<ResponseCorreios> objectsList = new ArrayList<>();
            StringBuilder jsonResponse = new StringBuilder();

            BufferedReader response = chamadaEndpoint(pathUrl);
            String inputLine;

            while ((inputLine = response.readLine()) != null) {
                jsonResponse.append(inputLine);
            }

            String jsonString = jsonResponse.toString();
            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonArray itensArray = jsonObject.getAsJsonArray("itens");

            if (itensArray == null) {
                throw new ZipCodeNotFoundException(HttpStatus.BAD_REQUEST);
            }

            for (JsonElement element : itensArray) {
                ResponseCorreios obj = gson.fromJson(element, ResponseCorreios.class);
                objectsList.add(obj);
            }
            return ResponseEntity.ok().body(objectsList);
        } catch (ZipCodeInvalidException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getStatus().toString());
            return ResponseEntity.status(e.getStatus()).body(errorResponse);
        }catch (ZipCodeNotFoundException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getStatus().toString());
            return ResponseEntity.status(e.getStatus()).body(errorResponse);
        }
    }

    public BufferedReader chamadaEndpoint(String pathUrl) throws Exception {
            URL url = new URL(pathUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json;odata=verbose");
            connection.setRequestProperty("Authorization", token);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                throw new ZipCodeNotFoundException(HttpStatus.NOT_FOUND);
            }

            BufferedReader response = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            return response;

    }

    public boolean validateCep(String cep) throws ZipCodeInvalidException {
        if(cep.length() != 8) {
            throw new ZipCodeInvalidException(Messages.ZIPCODE_INVALID.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return true;
    }
    public String formatCep(String zipCode){
        zipCode = zipCode.replaceAll("-","");
        return zipCode;
    }
}
