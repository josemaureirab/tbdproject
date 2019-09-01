package cl.rollers.tbdproject.SQL.controllers;

import cl.rollers.tbdproject.SQL.dto.RoleDto;
import cl.rollers.tbdproject.SQL.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        try{
            return ResponseEntity.ok(roleService.getAllRoles());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RoleDto> findRoleById (@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok(roleService.findRoleById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity create (@RequestBody RoleDto roleDto){
        try{
            return ResponseEntity.ok(roleService.createRole(roleDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity update (@PathVariable("id") Integer id, @RequestBody RoleDto roleDto){
        try{
            roleService.updateRoleData(roleDto, id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete (@PathVariable Integer id){
        try{
            roleService.deleteRole(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}