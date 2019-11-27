package cl.rollers.tbdproject.SQL.SQL2O.controllers;

import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryExcel;
import cl.rollers.tbdproject.SQL.SQL2O.services.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/read", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException
    {
        //String relativeWebPath = "WEB-INF/classes/static";
        //String absoluteFilePath = context.getRealPath(relativeWebPath);
        String absoluteFilePath = "src/main/resources/static/";
        String csvFileAddress = absoluteFilePath + file.getOriginalFilename();
        String fileNameWithOutExt = FilenameUtils.removeExtension(csvFileAddress);
        absoluteFilePath = fileNameWithOutExt + ".xlsx";
        File convertFile = new File(absoluteFilePath);
        try(FileOutputStream fout = fileService.csvToXlsx(file);) {
            fout.write(file.getBytes());
            fout.close();
            convertFile.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(convertFile);
            List<VoluntaryExcel> voluntaryExcelList = FileService.uploadXLSXFile(fileInputStream);
            this.saveVoluntaryExcelList(voluntaryExcelList);
            fileInputStream.close();
        }
        catch(IOException ex) {
            return new ResponseEntity<>("Exception occurred while zipping file", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("file is uploaded successfully", HttpStatus.OK);
    }

    private void saveVoluntaryExcelList(List<VoluntaryExcel> voluntaryExcelList){
        for (VoluntaryExcel voluntaryExcel: voluntaryExcelList
        ) {
            fileService.saveVoluntary(voluntaryExcel);
        }
    }
}
