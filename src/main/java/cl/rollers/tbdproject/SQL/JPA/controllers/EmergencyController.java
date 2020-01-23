
package cl.rollers.tbdproject.SQL.JPA.controllers;

import cl.rollers.tbdproject.SQL.JPA.dto.EmergencyDto;
import cl.rollers.tbdproject.SQL.JPA.services.EmergencyService;
import cl.rollers.tbdproject.SQL.SQL2O.features.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/emergencies")
public class EmergencyController {
    @Autowired
    private EmergencyService emergencyService;

    @GetMapping("/")
    @ResponseBody
    public ArrayList<Feature> getAllEmergencies(){
        return emergencyService.getAllEmergencies();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Feature findEmergencyById (@PathVariable("id") Integer id){
        return emergencyService.findEmergencyById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Feature create (@RequestBody Feature feature){
        return emergencyService.createEmergency(feature);
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity update (@PathVariable("id") Integer id, @RequestBody Feature feature){

        try{
            emergencyService.updateEmergency(feature, id);
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
            emergencyService.deleteEmergency(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/appendTask", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity appendTask (@RequestParam("emergencyId") Integer emergencyId, @RequestParam("taskId") Integer taskId){
        try{
            return ResponseEntity.ok(emergencyService.appendTask(emergencyId, taskId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/appendVoluntary", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity appendVoluntary (@RequestParam("emergencyId") Integer emergencyId, @RequestParam("voluntaryId") Integer voluntaryId){
        try{
            return ResponseEntity.ok(emergencyService.appendVoluntary(emergencyId, voluntaryId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}

