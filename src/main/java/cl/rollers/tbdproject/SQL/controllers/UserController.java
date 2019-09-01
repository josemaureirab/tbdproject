package cl.rollers.tbdproject.SQL.controllers;

import cl.rollers.tbdproject.SQL.dto.UserDto;
import cl.rollers.tbdproject.SQL.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<UserDto>> getAllUsers(){
        try{
            return ResponseEntity.ok(userService.getAllUsers());

        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> findUserById (@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok(userService.findUserById(id));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity create (@RequestBody UserDto userDto){

        try{
            return ResponseEntity.ok(userService.createUser(userDto));
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity update (@PathVariable("id") Integer id, @RequestBody UserDto userDto){

        try{
            userService.updateUserData(userDto, id);
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
            userService.deleteUser(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
