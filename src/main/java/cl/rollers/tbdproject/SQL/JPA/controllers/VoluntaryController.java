/*
package cl.rollers.tbdproject.SQL.JPA.controllers;

import cl.rollers.tbdproject.SQL.JPA.dto.VoluntaryDto;
import cl.rollers.tbdproject.SQL.JPA.services.VoluntaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sql2o.Sql2o;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/voluntaries")
public class VoluntaryController {

    private Sql2o sql2o[];
    public VoluntaryController(Sql2o[] sql2o) {
        this.sql2o = sql2o;
    }

    @Autowired
    private VoluntaryService voluntaryService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<VoluntaryDto>> getAllVoluntaries(){
        try{
            return ResponseEntity.ok(voluntaryService.getAllVoluntaries());

        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> findVoluntaryById (@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok(voluntaryService.findVoluntaryById(id));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity create (@RequestBody VoluntaryDto voluntaryDto){

        try{
            return ResponseEntity.ok(voluntaryService.createVoluntary(voluntaryDto));
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity update (@PathVariable("id") Integer id, @RequestBody VoluntaryDto voluntaryDto){

        try{
            voluntaryService.updateVoluntaryData(voluntaryDto, id);
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
            voluntaryService.deleteVoluntary(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
*/
