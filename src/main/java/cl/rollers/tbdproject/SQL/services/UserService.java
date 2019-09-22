package cl.rollers.tbdproject.SQL.services;

import cl.rollers.tbdproject.SQL.dao.UserDao;
import cl.rollers.tbdproject.SQL.mappers.UserMapper;
import cl.rollers.tbdproject.SQL.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.rollers.tbdproject.SQL.dto.UserDto;



import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDao userDao;

    public UserDto createUser(UserDto userDto){
        return userMapper.mapToDto(userDao.save(userMapper.mapToModel(userDto)));
    }

    public List<UserDto> getAllUsers(){
        List<User> userList = userDao.findAll();
        return userMapper.mapToDoArrayList(userList);
    }

    public UserDto findUserById(Integer id){
        if(userDao.findById(id).isPresent()){
            return userMapper.mapToDto(userDao.findById(id).get());
        }
        else{
            return null;
        }

    }
    public void updateUserData(UserDto userDto, Integer id){
        User userFinded = userDao.findUserById(id);
        userFinded.setName(userDto.getName());
        userFinded.setLastName(userDto.getLastName());
        userFinded.setRut(userDto.getRut());
        userFinded.setAge(userDto.getAge());
        userFinded.setRole(userDto.getRole());
        userDao.save(userFinded);
    }


    public void deleteUser(Integer id){
        User userFinded = userDao.findById(id).get();
        userDao.delete(userFinded);
    }

}
