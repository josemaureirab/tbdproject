package cl.rollers.tbdproject.SQL.controllers;

import cl.rollers.tbdproject.SQL.firstDataSource.dto.FDSVoluntaryDto;
import cl.rollers.tbdproject.SQL.firstDataSource.services.FDSVoluntaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/voluntaries")

public class VoluntaryController {
    @Autowired
    private FDSVoluntaryService voluntaryService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<FDSVoluntaryDto>> getAllVoluntaries(){
        try{
            return ResponseEntity.ok(voluntaryService.getAllFDSVoluntaries());

        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> findVoluntaryById (@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok(voluntaryService.findFDSVoluntaryById(id));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity create (@RequestBody FDSVoluntaryDto voluntaryDto){

        try{
            return ResponseEntity.ok(voluntaryService.createFDSVoluntary(voluntaryDto));
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity update (@PathVariable("id") Integer id, @RequestBody FDSVoluntaryDto voluntaryDto){

        try{
            voluntaryService.updateFDSVoluntaryData(voluntaryDto, id);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete (@PathVariable Integer id){

        try{
            voluntaryService.deleteFDSVoluntary(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
