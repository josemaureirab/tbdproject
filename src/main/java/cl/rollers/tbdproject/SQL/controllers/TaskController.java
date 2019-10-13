package cl.rollers.tbdproject.SQL.controllers;

import org.springframework.http.HttpStatus;
import cl.rollers.tbdproject.SQL.firstDataSource.dto.FDSTaskDto;
import cl.rollers.tbdproject.SQL.firstDataSource.services.FDSTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private FDSTaskService taskService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<FDSTaskDto>> getAllTasks(){

        try{
            return ResponseEntity.ok(taskService.getAllFDSTasks());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<FDSTaskDto> findTaskById (@PathVariable("id") Integer id){

        try{
            return ResponseEntity.ok(taskService.findFDSTaskById(id));
        }

        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity create (@RequestBody FDSTaskDto taskDto){

        try{
            return ResponseEntity.ok(taskService.createFDSTask(taskDto));
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity update (@PathVariable("id") Integer id, @RequestBody FDSTaskDto taskDto){

        try{
            taskService.updateFDSTaskData(taskDto, id);
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
            taskService.deleteFDSTask(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
