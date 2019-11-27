/*
package cl.rollers.tbdproject.SQL.JPA.controllers;

import cl.rollers.tbdproject.SQL.JPA.dto.VoluntaryEmergencyDto;
import cl.rollers.tbdproject.SQL.JPA.services.VoluntaryEmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/voluntaryWithEmergency", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity voluntaryWithEmergency (@RequestParam("voluntaryId") Integer voluntaryId,
                                                  @RequestParam("emergencyId") Integer emergencyId){
        try{
            return ResponseEntity.ok(voluntaryEmergencyService.createVoluntaryWithEmergency(voluntaryId, emergencyId));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}*/
