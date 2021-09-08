package com.infosys.demoreadexcel.helper;


import com.infosys.demoreadexcel.model.Excel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static String TYPE="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Id", "Typegl","Typeexpanse","Rccode"};
    static String SHEET = "Example Excel";

    public static boolean hasExcelFormat(MultipartFile file){
        if (!TYPE.equals(file.getContentType())){
            return false;
        }

        return true;
    }

    public static List<Excel> excelToBudget(InputStream is){
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet;
            sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();

            List<Excel> excelList = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()){
                Row currentRow = rows.next();

                if (rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Excel excel = new Excel();

                int cellIdx = 0;
                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx){
//
                        case 0:
                            excel.setTypeGl(currentCell.getStringCellValue());
                            break;
                        case 1:
                            excel.setTypeExpense(currentCell.getStringCellValue());
                            break;
                        case 2:
                            excel.setRcCode(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }
                    cellIdx++;
                }
                excelList.add(excel);
            }
            workbook.close();

            return excelList;
        }catch (IOException e){
            throw new RuntimeException("fail to farse Excel file : "+ e.getMessage());
        }
    }

    public static ByteArrayInputStream excampleToExcel(List<Excel> budgetList){
        try {
            Workbook workbook = new XSSFWorkbook();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int i=0; i < HEADERs.length; i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(HEADERs[i]);
            }

            int rowIndex = 1;

            for (Excel excel : budgetList){
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(excel.getId());
                row.createCell(1).setCellValue(excel.getTypeGl());
                row.createCell(2).setCellValue(excel.getTypeExpense());
                row.createCell(3).setCellValue(excel.getRcCode());
            }

            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }catch (IOException e){
            throw new RuntimeException("Fail to import data : "+ e.getMessage());
        }
    }
}
