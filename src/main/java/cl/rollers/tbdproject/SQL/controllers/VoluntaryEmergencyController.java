package cl.rollers.tbdproject.SQL.controllers;

import cl.rollers.tbdproject.SQL.dto.VoluntaryEmergencyDto;
import cl.rollers.tbdproject.SQL.services.VoluntaryEmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/voluntaryEmergencies")
public class VoluntaryEmergencyController {
    @Autowired
    private VoluntaryEmergencyService voluntaryEmergencyService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<VoluntaryEmergencyDto>> getAllVoluntaryEmergencies(){
        try{
            return ResponseEntity.ok(voluntaryEmergencyService.getAllVoluntaryEmergencies());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<VoluntaryEmergencyDto> findVoluntaryEmergencyById (@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok(voluntaryEmergencyService.findVoluntaryEmergencyById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity createVoluntaryEmergency (@RequestBody VoluntaryEmergencyDto voluntaryEmergencyDto){
        try{
            return ResponseEntity.ok(voluntaryEmergencyService.createVoluntaryEmergency(voluntaryEmergencyDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity updateVoluntaryEmergency (@PathVariable("id") Integer id, @RequestBody VoluntaryEmergencyDto voluntaryEmergencyDto){
        try{
            voluntaryEmergencyService.updateVoluntaryEmergencyData(voluntaryEmergencyDto, id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity deleteVoluntaryEmergency (@PathVariable Integer id){
        try{
            voluntaryEmergencyService.deleteVoluntaryEmergency(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}