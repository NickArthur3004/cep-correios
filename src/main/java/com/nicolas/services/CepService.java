package com.nicolas.services;

import com.google.gson.Gson;
import com.nicolas.exceptions.ZipCodeNotFoundException;
import com.nicolas.responses.ResponseCorreios;
import com.nicolas.models.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CepService {


    private String token = "Bearer " + "Token para a requisição";
    static String webService = "https://api.correios.com.br/cep/v2/enderecos/";

    public ResponseCorreios findZipCode(String cep) throws Exception {

        cep = formatCep(cep);

        String pathUrl = webService + cep;

        try {
            URL url = new URL(pathUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json;odata=verbose");
                connection.setRequestProperty("Authorization", token);

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_NOT_FOUND){
                throw new ZipCodeNotFoundException("ZIP code not found: " + cep);
            }

            BufferedReader response = new BufferedReader(new InputStreamReader((connection.getInputStream())));

            String jsonEmString = Util.converteJsonEmString(response);

            Gson gson = new Gson();
            ResponseCorreios responseCorreios = gson.fromJson(jsonEmString, ResponseCorreios.class);

            return responseCorreios;

        }catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

    public String formatCep(String zipCode){
        zipCode = zipCode.replaceAll("-","");
        return zipCode;
    }
}
