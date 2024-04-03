package com.nicolas.cepSpring.services;


import com.google.gson.*;
import com.nicolas.cepSpring.exceptions.ZipCodeNotFoundException;
import com.nicolas.cepSpring.models.Util;
import com.nicolas.cepSpring.responses.ResponseCorreios;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CepService {
    private String token = "Bearer " + "eyJhbGciOiJSUzUxMiJ9.eyJhbWJpZW50ZSI6IlBST0RVQ0FPIiwiaWQiOiI0NzE3MzI5NDAwMDExNyIsInBmbCI6IlBKIiwiY25waiI6IjQ3MTczMjk0MDAwMTE3IiwiY2FydGFvLXBvc3RhZ2VtIjp7Im51bWVybyI6IjAwNzc2NjIwNTkiLCJjb250cmF0byI6Ijk5MTI2MDMxNDIiLCJkciI6NzIsImFwaSI6WzI3LDM0LDM1LDM2LDM3LDQxLDc2LDc4LDgwLDgzLDg3LDkzLDU2Niw1ODddfSwiaXAiOiI4LjIwOS42Ny4yMCwgMTkyLjE2OC4xLjEzMCIsImlhdCI6MTcxMjE0MjUzNiwiaXNzIjoidG9rZW4tc2VydmljZSIsImV4cCI6MTcxMjIyODkzNiwianRpIjoiNjQxNzNjNTYtOTcyOC00MmVkLTk5ZDMtOTE0NGYyYjhjOTY3In0.trg835EmaDd7gd3ex4pkt6rfIER-nbWJSgvUTUBL9Y6lrtcAygj0UkKaJ4Qbkst1UPUJp0oqoj4YMvTL3K4-9q8U6INJGTaZlQy5F4My3pVQ3VTeUoH1pMFEKpOmqFWyAbyq5wubPG6RJBgZbSHrvjIHoo8OuyIPUdUxVW-1X88MDsHT4bactOIo8LC01dfC6EQCy58tuaDAPuVUWzssO3vD1upfpSYhQ3plRoGC8BRZkDSY2TUL2mhQj6NUxsLy7K2YhfuEJtApvpLkzeAqxFdPKYgDVRj_Za_AHyTiblzfcm4ubaYkNMMytil-L8lsVQV94aXn9-qjx11begkW6Q";
    static String webService = "https://api.correios.com.br/cep/v2/enderecos";

    public ResponseCorreios findZipCode(String cep) throws Exception {

        cep = formatCep(cep);

        String pathUrl = webService + "/" + cep;

        try {
            BufferedReader response = chamadaEndpoint(pathUrl);

            String jsonEmString = Util.converteJsonEmString(response);

            Gson gson = new Gson();
            ResponseCorreios responseCorreios = gson.fromJson(jsonEmString, ResponseCorreios.class);

            return responseCorreios;

        }catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

    public List<ResponseCorreios> findZipCodesByRange(String cepInicial, String cepFinal) throws Exception {

        String pathUrl = webService + "?" +
                "cepInicial="+ cepInicial +
                "&cepFinal="+ cepFinal +
                "&page=" + 0 +
                "&size=" + 50;
        try {
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

            for (JsonElement element : itensArray) {
                ResponseCorreios obj = gson.fromJson(element, ResponseCorreios.class);
                objectsList.add(obj);
            }
            return objectsList;

        }catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

    public String formatCep(String zipCode){
        zipCode = zipCode.replaceAll("-","");
        return zipCode;
    }

    public BufferedReader chamadaEndpoint(String pathUrl) throws Exception {
        try {
            URL url = new URL(pathUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json;odata=verbose");
            connection.setRequestProperty("Authorization", token);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                throw new ZipCodeNotFoundException();
            }

            BufferedReader response = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            return response;
        }
        catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
}
