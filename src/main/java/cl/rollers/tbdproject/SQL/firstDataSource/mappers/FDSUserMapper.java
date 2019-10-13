package cl.rollers.tbdproject.SQL.firstDataSource.mappers;

import cl.rollers.tbdproject.SQL.firstDataSource.dto.FDSUserDto;
import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Component
public class FDSUserMapper {
    public FDSUser mapToModel(FDSUserDto userDto){
        FDSUser user = new FDSUser();
        user.setId(userDto.getId().longValue());
        user.setUsername(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setRut(userDto.getRut());
        user.setAge(userDto.getAge());
        return user;
    }

    public FDSUserDto mapToDto(FDSUser user){
        FDSUserDto userDto = new FDSUserDto();
        userDto.setId(user.getId().intValue());
        userDto.setUserName(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setRut(user.getRut());
        userDto.setAge(user.getAge());
        return userDto;
    }

    public List<FDSUserDto> mapToDtoList(List<FDSUser> user) {
        int i;
        ArrayList<FDSUserDto> usersDto = new ArrayList<>();
        for(i=0;i<user.size();i++){
            usersDto.add(mapToDto(user.get(i)));
        }
        return usersDto;
    }
}