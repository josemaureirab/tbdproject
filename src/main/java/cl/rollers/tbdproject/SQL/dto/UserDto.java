package cl.rollers.tbdproject.SQL.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties({"saleList", "entryList", "password"})
public class UserDto {
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String lastName;
    private String rut;
    private Integer age;
}
