package com.nicolas.cepSpring.models;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {

    public static String converteJsonEmString(BufferedReader bufferedReader) throws IOException{
        String response, jsonEmString = "";
        while((response = bufferedReader.readLine()) != null){
            jsonEmString += response;
        }
        return jsonEmString;
    }
}
