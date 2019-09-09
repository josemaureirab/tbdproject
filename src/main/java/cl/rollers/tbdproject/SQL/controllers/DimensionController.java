package cl.rollers.tbdproject.SQL.controllers;

import cl.rollers.tbdproject.SQL.dto.DimensionDto;
import cl.rollers.tbdproject.SQL.services.DimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dimensions")
public class DimensionController {
    @Autowired
    private DimensionService dimensionService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<DimensionDto>> getAllDimensions(){
        try{
            return ResponseEntity.ok(dimensionService.getAllDimensions());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DimensionDto> findDimensionById (@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok(dimensionService.findDimensionById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity createDimension (@RequestBody DimensionDto dimensionDto){
        try{
            return ResponseEntity.ok(dimensionService.createDimension(dimensionDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity updateDimension (@PathVariable("id") Integer id, @RequestBody DimensionDto dimensionDto){
        try{
            dimensionService.updateDimensionData(dimensionDto, id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity deleteDimension (@PathVariable Integer id){
        try{
            dimensionService.deleteDimension(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
