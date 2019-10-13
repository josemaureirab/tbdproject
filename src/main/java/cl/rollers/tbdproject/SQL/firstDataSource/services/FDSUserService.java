package cl.rollers.tbdproject.SQL.firstDataSource.services;

import cl.rollers.tbdproject.SQL.firstDataSource.dao.FDSUserDao;
import cl.rollers.tbdproject.SQL.firstDataSource.dto.FDSUserDto;
import cl.rollers.tbdproject.SQL.firstDataSource.mappers.FDSUserMapper;
import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("Duplicates")
@Service
public class FDSUserService {
    @Autowired
    private FDSUserMapper userMapper;

    @Autowired
    private FDSUserDao userDao;

    public FDSUserDto createFDSUser(FDSUserDto userDto){
        return userMapper.mapToDto(userDao.save(userMapper.mapToModel(userDto)));
    }

    public List<FDSUserDto> getAllFDSUsers(){
        List<FDSUser> userList = userDao.findAll();
        return userMapper.mapToDtoList(userList);
    }

    public FDSUserDto findFDSUserById(Integer id){
        if(userDao.findById(id) != null){
            return userMapper.mapToDto(userDao.findById(id));
        }
        else{
            return null;
        }

    }
    public void updateFDSUserData(FDSUserDto userDto, Integer id){
      FDSUser userFinded = userDao.findFDSUserById(id);
        userFinded.setUsername(userDto.getUserName());
        userFinded.setPassword(userDto.getPassword());
        userFinded.setName(userDto.getName());
        userFinded.setLastName(userDto.getLastName());
        userFinded.setRut(userDto.getRut());
        userFinded.setAge(userDto.getAge());
        /*userFinded.setRole(userDto.getRole());*/
        userDao.save(userFinded);
    }


    public void deleteUser(Integer id){
      FDSUser userFinded = userDao.findById(id);
        userDao.delete(userFinded);
    }

}
