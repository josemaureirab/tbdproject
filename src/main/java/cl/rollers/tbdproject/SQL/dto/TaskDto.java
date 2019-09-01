package cl.rollers.tbdproject.SQL.dto;


import lombok.Data;

@Data
public class TaskDto {

    private Integer id;

    private String name;

    private String description;

    private Boolean status;
}
