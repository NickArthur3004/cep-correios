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
    private String token = "Bearer " + "Token de acesso a api correios";
    static String webService = "Url da api que deseja usar";

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
