package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;

@Data
public class RoleDto {
    private Integer id;
    private String name;
    @JsonIgnore
    private List<User> userList;
}