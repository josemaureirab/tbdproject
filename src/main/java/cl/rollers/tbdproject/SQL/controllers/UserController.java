package cl.rollers.tbdproject.SQL.controllers;

import cl.rollers.tbdproject.SQL.firstDataSource.dao.FDSUserDao;
import cl.rollers.tbdproject.SQL.firstDataSource.dto.FDSUserDto;
import cl.rollers.tbdproject.SQL.firstDataSource.mappers.FDSUserMapper;
import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private FDSUserDao userDao;

    @Autowired
    private FDSUserMapper userMapper;

    @GetMapping("/")
    public ResponseEntity<List<FDSUserDto>> getAllUsers(){
        try{
            return ResponseEntity.ok(userMapper.mapToDtoList(userDao.findAll()));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity updateUserData(@RequestBody FDSUser user, @PathVariable long id){

        try{
            FDSUser userFinded = userDao.findById(id);
            userFinded.setName(user.getName());
            userFinded.setLastName(user.getLastName());
            userFinded.setUsername(user.getUsername());
            userFinded.setPassword(user.getPassword());
            return ResponseEntity.ok(userMapper.mapToDto(userDao.save(userFinded)));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping()
    public ResponseEntity<FDSUserDto> createGuest(@RequestBody FDSUser user){

        try{
            return ResponseEntity.ok(userMapper.mapToDto(userDao.save(user)));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser (@PathVariable int id){

        try{
            userDao.delete(userDao.findById(id));
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
