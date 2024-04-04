package com.nicolas.cepSpring.execs;



import com.nicolas.cepSpring.models.Address;
import com.nicolas.cepSpring.responses.ResponseCorreios;
import com.nicolas.cepSpring.services.CepService;
import com.nicolas.cepSpring.services.ExcelService;

import java.util.ArrayList;
import java.util.List;

public class ExecAPICorreios {

    /*public static final String readFile = "Caminho do arquivo que será lido";
    CepService cepService = new CepService();
    ExcelService excelService= new ExcelService();

    *//*
    Esse metodo busca uma planilha com ceps da sua maquina, busca um por um na api dos correios
    e retorna uma nova planilha com todos os ceps e informações adicionais.
     *//*
    public void execBuscaCeps() throws Exception {
        List<String> notFoundZipCodes = new ArrayList<>();

        System.out.println("Programa Iniciado!");
        List<Address> listAddress;
        List<ResponseCorreios> responses = new ArrayList<>();

        listAddress = excelService.readDocExcel(readFile);

        if(listAddress.isEmpty()){
            System.out.println("Addresses not found");
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
    }*/
}
