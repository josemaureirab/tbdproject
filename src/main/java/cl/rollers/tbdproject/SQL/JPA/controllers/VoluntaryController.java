package cl.rollers.tbdproject.SQL.JPA.controllers;

import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.JPA.dto.VoluntaryDto;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.JPA.services.VoluntaryService;
import cl.rollers.tbdproject.SQL.SQL2O.features.Feature;
import cl.rollers.tbdproject.SQL.SQL2O.features.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sql2o.Sql2o;

import java.util.HashMap;
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

    @Autowired
    private VoluntaryDao voluntaryDao;

    @GetMapping("/")
    @ResponseBody
    public FeatureCollection getAllVoluntaries(){
        FeatureCollection featureCollection = new FeatureCollection();
        return voluntaryService.getAllVoluntaries();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FeatureCollection findVoluntaryById (@PathVariable("id") Integer id){
        return voluntaryService.findVoluntaryById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public FeatureCollection create (@RequestBody FeatureCollection featureCollection){
        return voluntaryService.createVoluntary(featureCollection);
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