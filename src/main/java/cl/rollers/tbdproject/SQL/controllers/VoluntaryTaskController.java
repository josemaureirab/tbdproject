package cl.rollers.tbdproject.SQL.controllers;

import cl.rollers.tbdproject.SQL.dto.VoluntaryTaskDto;
import cl.rollers.tbdproject.SQL.services.VoluntaryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/voluntaryTasks")
public class VoluntaryTaskController {
    @Autowired
    private VoluntaryTaskService voluntaryTaskService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<VoluntaryTaskDto>> getAllVoluntaryTasks(){
        try{
            return ResponseEntity.ok(voluntaryTaskService.getAllVoluntaryTasks());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<VoluntaryTaskDto> findVoluntaryTaskById (@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok(voluntaryTaskService.findVoluntaryTaskById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity createVoluntaryTask(@RequestBody VoluntaryTaskDto voluntaryTaskDto){
        try{
            return ResponseEntity.ok(voluntaryTaskService.createVoluntaryTask(voluntaryTaskDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity updateVoluntaryTask(@PathVariable("id") Integer id, @RequestBody VoluntaryTaskDto voluntaryTaskDto){
        try{
            voluntaryTaskService.updateVoluntaryTaskData(voluntaryTaskDto, id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity deleteVoluntaryTask(@PathVariable Integer id){
        try{
            voluntaryTaskService.deleteVoluntaryTask(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/voluntaryWithTask", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity voluntaryWithTask (@RequestParam("voluntaryId") Integer voluntaryId,
                                                  @RequestParam("taskId") Integer taskId){
        try{
            return ResponseEntity.ok(voluntaryTaskService.createVoluntaryWithTask(voluntaryId, taskId));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}