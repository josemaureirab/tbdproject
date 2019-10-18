package cl.rollers.tbdproject.SQL.controllers;

import org.springframework.http.HttpStatus;
import cl.rollers.tbdproject.SQL.dto.TaskDto;
import cl.rollers.tbdproject.SQL.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sql2o.Sql2o;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private Sql2o sql2o[];
    public TaskController(Sql2o[] sql2o) {
        this.sql2o = sql2o;
    }

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<TaskDto>> getAllTasks(){

        try{
            return ResponseEntity.ok(taskService.getAllTasks());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TaskDto> findTaskById (@PathVariable("id") Integer id){

        try{
            return ResponseEntity.ok(taskService.findTaskById(id));
        }

        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity create (@RequestBody TaskDto taskDto){

        try{
            return ResponseEntity.ok(taskService.createTask(taskDto));
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity update (@PathVariable("id") Integer id, @RequestBody TaskDto taskDto){

        try{
            taskService.updateTaskData(taskDto, id);
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
            taskService.deleteTask(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
