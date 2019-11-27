package cl.rollers.tbdproject.SQL.JPA.mappers;

import cl.rollers.tbdproject.SQL.JPA.dto.UserDto;
import cl.rollers.tbdproject.SQL.JPA.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Component
public class UserMapper {
    public User mapToModel(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId().longValue());
        user.setBirthDate(userDto.getBirthDate());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setActive(userDto.getActive());
        return user;
    }

    public UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId().intValue());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setActive(user.getActive());
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