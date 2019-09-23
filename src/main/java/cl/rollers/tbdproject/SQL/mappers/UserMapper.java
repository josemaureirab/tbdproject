package cl.rollers.tbdproject.SQL.mappers;

import cl.rollers.tbdproject.SQL.dto.UserDto;
import cl.rollers.tbdproject.SQL.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Component
public class UserMapper {
    public User mapToModel(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId().longValue());
        user.setUsername(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setRut(userDto.getRut());
        user.setAge(userDto.getAge());
        return user;
    }

    public UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId().intValue());
        userDto.setUserName(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setRut(user.getRut());
        userDto.setAge(user.getAge());
        return userDto;
    }

    public List<UserDto> mapToDtoList(List<User> user) {
        int i;
        ArrayList<UserDto> usersDto = new ArrayList<>();
        for(i=0;i<user.size();i++){
            usersDto.add(mapToDto(user.get(i)));
        }
        return usersDto;
    }
}