package cl.rollers.tbdproject.SQL.controllers;

import cl.rollers.tbdproject.SQL.dto.VoluntaryDimensionDto;
import cl.rollers.tbdproject.SQL.services.VoluntaryDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/voluntaryDimensions")
public class VoluntaryDimensionController {
    @Autowired
    private VoluntaryDimensionService voluntaryDimensionService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<VoluntaryDimensionDto>> getAllVoluntaryDimension(){
        try{
            return ResponseEntity.ok(voluntaryDimensionService.getAllVoluntaryDimensions());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<VoluntaryDimensionDto> findVoluntaryDimensionById (@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok(voluntaryDimensionService.findVoluntaryDimensionById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity createVoluntaryDimension (@RequestBody VoluntaryDimensionDto roleDto){
        try{
            return ResponseEntity.ok(voluntaryDimensionService.createVoluntaryDimension(roleDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity updateVoluntaryDimension (@PathVariable("id") Integer id, @RequestBody VoluntaryDimensionDto roleDto){
        try{
            voluntaryDimensionService.updateVoluntaryDimensionData(roleDto, id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity deleteVoluntaryDimension (@PathVariable Integer id){
        try{
            voluntaryDimensionService.deleteVoluntaryDimension(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}