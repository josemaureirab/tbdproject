package cl.rollers.tbdproject.SQL.SQL2O.services;

import cl.rollers.tbdproject.SQL.SQL2O.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.SQL2O.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {

    @Autowired
    private VoluntaryDao voluntaryDao;

    public static List<VoluntaryExcel> uploadXLSXFile(InputStream file) throws IOException {
        //InputStream ExcelFileToRead = new FileInputStream("src/main/resources/static/keywords.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;

        Iterator rows = sheet.rowIterator();
        rows.next();
        List<VoluntaryExcel> voluntaryExcels = new ArrayList<>();
        rows.hasNext();
        Integer counter = 1;
        while (rows.hasNext())
        {
            VoluntaryExcel voluntaryExcel = new VoluntaryExcel();
            row = (XSSFRow) rows.next();
            voluntaryExcel.setId(getIntegerValue(row.getCell(0)));
            voluntaryExcel.setName(row.getCell(1).getStringCellValue());
            voluntaryExcel.setLastName(row.getCell(2).getStringCellValue());
            voluntaryExcel.setMail(row.getCell(3).getStringCellValue());
            voluntaryExcel.setGender(row.getCell(4).getStringCellValue());
            voluntaryExcel.setDimensionList(row.getCell(5).getStringCellValue());
            voluntaryExcel.setRequirementList(row.getCell(6).getStringCellValue());
            voluntaryExcel.setLatitude(getFloatValue(row.getCell(7)));
            voluntaryExcel.setLongitude(getFloatValue(row.getCell(8)));
            voluntaryExcels.add(voluntaryExcel);
            System.out.println("Number: " + counter + " " + voluntaryExcel.toString());
            counter++;
        }

        return voluntaryExcels;
    }

    public void saveVoluntary(VoluntaryExcel voluntaryExcel){
        Voluntary voluntary = this.makeProductHelper(voluntaryExcel);
        voluntaryDao.save(voluntary);
    }

    private Voluntary makeProductHelper(VoluntaryExcel voluntaryExcel){
        Voluntary voluntary = new Voluntary();
        voluntary.setId(voluntaryExcel.getId());
        voluntary.setName(voluntaryExcel.getName());
        voluntary.setLastName(voluntaryExcel.getLastName());
        voluntary.setMail(voluntaryExcel.getMail());
        voluntary.setGender(voluntaryExcel.getGender());
        /*voluntary.setLatitude(voluntaryExcel.getLatitude());
        voluntary.setLongitude(voluntaryExcel.getLongitude());*/
        return voluntary;
    }

    public static int getIntegerValue(Cell cell){
        switch (cell.getCellType()) {
            case STRING:
                return Integer.parseInt(cell.getRichStringCellValue().getString());
            case NUMERIC:
                return (int)(cell.getNumericCellValue());
            default:
                return -1;
        }
    }

    public static Float getFloatValue(Cell cell){
        switch (cell.getCellType()) {
            case STRING:
                return Float.parseFloat(cell.getRichStringCellValue().getString());
            case NUMERIC:
                return (float)(cell.getNumericCellValue());
            default:
                return Float.parseFloat("0");
        }
    }

    public FileOutputStream csvToXlsx(MultipartFile file) {

        try {
            String absoluteFilePath = "src/main/resources/static/";
            String csvFileAddress = absoluteFilePath + file.getOriginalFilename();
            String fileNameWithOutExt = FilenameUtils.removeExtension(csvFileAddress);
            String xlsxFileAddress = fileNameWithOutExt + ".xlsx";

            SXSSFWorkbook workBook = new SXSSFWorkbook(1000);
            org.apache.poi.ss.usermodel.Sheet sheet = workBook.createSheet("sheet1");
            String currentLine = null;
            int RowNum = -1;
            BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
            int count = 0;
            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split(",");
                StringBuilder dimensionRequirements = new StringBuilder();
                RowNum++;
                Row currentRow = sheet.createRow(RowNum);
                if(count == 0) {
                    for (int i = 0; i < str.length; i++) {
                        currentRow.createCell(i)
                                .setCellValue(str[i]);
                    }
                }else{
                    for (int i = 0; i < 5; i++) {
                        currentRow.createCell(i)
                                .setCellValue(str[i]);
                    }
                    for (int i = 5; i < str.length-2; i++) {
                        dimensionRequirements.append(str[i]);
                    }
                    String dimReqString = dimensionRequirements.toString();
                    String dimReq[] = dimReqString.split("]");
                    System.out.println(dimReq[0]);
                    System.out.println(dimReq[1]);
                    currentRow.createCell(5)
                            .setCellValue(dimReq[0]);
                    currentRow.createCell(6)
                            .setCellValue(dimReq[1]);
                    currentRow.createCell(7)
                            .setCellValue(str[str.length-2]);
                    currentRow.createCell(8)
                            .setCellValue(str[str.length-1]);
                }
                count+=1;
            }
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd-HHmmss");
            Date today = Calendar.getInstance()
                    .getTime();
            String reportDate = df.format(today);
            FileOutputStream fileOutputStream = new FileOutputStream(xlsxFileAddress);
            workBook.write(fileOutputStream);
            return fileOutputStream;
            //System.out.println( "Done" );
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Exception in try");
        }
        return null;
    }
}
