package cl.rollers.tbdproject.SQL.SQL2O.controllers;

import cl.rollers.tbdproject.SQL.SQL2O.features.Feature;
import cl.rollers.tbdproject.SQL.SQL2O.features.FeatureCollection;
import cl.rollers.tbdproject.SQL.SQL2O.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.SQL2O.dto.VoluntaryDto;
import cl.rollers.tbdproject.SQL.SQL2O.features.GeoJSONSerializer;
import cl.rollers.tbdproject.SQL.SQL2O.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.services.VoluntaryService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    public VoluntaryController(Sql2o[] sql2o2) {
        this.sql2o = sql2o2;
    }

    @Autowired
    private VoluntaryService voluntaryService;

    @Autowired
    private VoluntaryDao voluntaryDao;

    @GetMapping("/")
    @ResponseBody
    @JsonSerialize(using = GeoJSONSerializer.class)
    public FeatureCollection getAllVoluntaries(){

        List<VoluntaryDto> voluntaries;
        voluntaries = voluntaryService.getAllVoluntaries();
        System.out.println(voluntaries.get(1));
        if(voluntaries.isEmpty()){
            return new FeatureCollection();
        }
            FeatureCollection featureCollection = new FeatureCollection();
            for (VoluntaryDto voluntary : voluntaries) {
                HashMap<String, Object> propierties = new HashMap<>();
                propierties.put("age", voluntary.getAge());
                propierties.put("name", voluntary.getName());
                propierties.put("lastname", voluntary.getLastName());
                propierties.put("gender", voluntary.getGender());
                propierties.put("id", voluntary.getId());
                propierties.put("mail", voluntary.getMail());
                propierties.put("rut", voluntary.getRut());
                featureCollection.addFeature(new Feature(voluntary.getLocation(), propierties));
            }
            return featureCollection;
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
    public ResponseEntity create (@RequestBody VoluntaryDto voluntaryDto, @RequestParam String location){
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
