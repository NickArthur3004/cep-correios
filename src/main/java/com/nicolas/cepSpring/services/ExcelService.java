package com.nicolas.cepSpring.services;


import com.nicolas.cepSpring.execs.ExecAPICorreios;
import com.nicolas.cepSpring.models.Address;
import com.nicolas.cepSpring.responses.ResponseCorreios;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    List<Address> listAddress = new ArrayList<Address>();
    public List<Address> readDocExcel(String fileName) throws IOException{
        try{
            FileInputStream file = new FileInputStream(new File(ExecAPICorreios.readFile));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheetAddress = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheetAddress.iterator();

            while (rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                Address address = new Address();
                listAddress.add(address);
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch(cell.getColumnIndex()){
                        case 0:
                            address.setZipCode(cell.getStringCellValue());
                    }
                }
            }
            file.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listAddress;
    }

    public String generatorDocExcel(List<ResponseCorreios> listResponses) throws IOException{
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheetZipCode = workbook.createSheet("CorreioZipCodes");
        FileOutputStream fos = null;

        File newFile = new File("caminho e nome do novo arquivo .xlsx");
        try{
            fos = new FileOutputStream(newFile);
            int i = 0;
            XSSFRow row1 = sheetZipCode.createRow(i);
            row1.createCell(0).setCellValue("ZIP CODE");
            row1.createCell(1).setCellValue("FU");
            row1.createCell(2).setCellValue("NEIGHBORHOOD");
            row1.createCell(3).setCellValue("PUBLIC PLACE");
            row1.createCell(4).setCellValue("LOCALITY");
            i++;


        for(ResponseCorreios response : listResponses){
            XSSFRow row = sheetZipCode.createRow(i);
            row.createCell(0).setCellValue(response.getCep());
            row.createCell(1).setCellValue(response.getUf());
            row.createCell(2).setCellValue(response.getBairro());
            row.createCell(3).setCellValue(response.getLogradouro());
            row.createCell(4).setCellValue(response.getLocalidade());
            i++;
        }
        workbook.write(fos);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }finally {
            try{
                fos.flush();
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "Excel file created successfully !!!";
    }
}
