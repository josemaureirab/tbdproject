package cl.rollers.tbdproject.SQL.services;

import cl.rollers.tbdproject.SQL.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import cl.rollers.tbdproject.SQL.models.VoluntaryExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        voluntary.setLatitude(voluntaryExcel.getLatitude());
        voluntary.setLongitude(voluntaryExcel.getLongitude());
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

}
