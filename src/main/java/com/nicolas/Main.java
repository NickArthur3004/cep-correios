package com.nicolas;

import com.nicolas.exceptions.ZipCodeNotFoundException;
import com.nicolas.models.Address;
import com.nicolas.responses.ResponseCorreios;
import com.nicolas.services.CepService;
import com.nicolas.services.ExcelService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String readFile = "Caminho do arquivo que será lido";

    public static void main(String[] args) throws Exception {

        List<String> notFoundZipCodes = new ArrayList<>();

        CepService cepService = new CepService();
        ExcelService excelService= new ExcelService();

        System.out.println("Programa Iniciado!");
        List<Address> listAddress = new ArrayList<Address>();
        List<ResponseCorreios> responses = new ArrayList<ResponseCorreios>();

        listAddress = excelService.readDocExcel(readFile);

        if(listAddress.isEmpty()){
            System.out.println("Address not found");
        }else {
            int numAdd = 1;
            for(Address a : listAddress){
                try{
                    responses.add(cepService.findZipCode(a.getZipCode()));
                    numAdd++;
                    System.out.println(numAdd + ": " + notFoundZipCodes.size());
                }catch (Exception e){
                    notFoundZipCodes.add(a.getZipCode());
                }
            }

            System.out.println("Zip codes not found: {");
            for (String z : notFoundZipCodes){
                System.out.println(z + ",");
            }
            System.out.println("}");
            String message = excelService.generatorDocExcel(responses);
            System.out.println(message);
        }
    }
}