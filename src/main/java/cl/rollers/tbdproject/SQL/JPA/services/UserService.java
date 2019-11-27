package cl.rollers.tbdproject.SQL.JPA.services;

/*import cl.rollers.tbdproject.SQL.SQL2O.dao.UserDao;
import cl.rollers.tbdproject.SQL.SQL2O.mappers.UserMapper;
import cl.rollers.tbdproject.SQL.SQL2O.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.rollers.tbdproject.SQL.SQL2O.dto.UserDto;



import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Service*/
public class UserService {
    /*@Autowired
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
        userFinded.setUsername(userDto.getUserName());
        userFinded.setPassword(userDto.getPassword());
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
    }*/

}
