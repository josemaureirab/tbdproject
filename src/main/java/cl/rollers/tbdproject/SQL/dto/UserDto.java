package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.Role;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String lastName;
    private String rut;
    private Integer age;
    private Role role;
}
