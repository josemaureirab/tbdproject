package cl.rollers.tbdproject.SQL.mappers;

import cl.rollers.tbdproject.SQL.dto.UserDto;
import cl.rollers.tbdproject.SQL.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public User mapToModel(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setRut(userDto.getRut());
        user.setAge(userDto.getAge());
        return user;
    }

    public UserDto mapToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setRut(user.getRut());
        userDto.setAge(user.getAge());
        return userDto;
    }

    public List<UserDto> mapToDoArrayList(List<User> userList){
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: userList){
            UserDto userDto = new UserDto();
            userDto = this.mapToDto(user);
            userDtoList.add(userDto);

        }
        return userDtoList;

    }

}
