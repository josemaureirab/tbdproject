package cl.rollers.tbdproject.SQL.JPA.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties({"saleList", "entryList", "password"})
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Date birthDate;
    private Boolean active;
}
