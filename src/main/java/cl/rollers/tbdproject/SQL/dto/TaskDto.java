package cl.rollers.tbdproject.SQL.dto;


import cl.rollers.tbdproject.SQL.models.Emergency;
import lombok.Data;

@Data
public class TaskDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;
    private Integer emergency_id;
}
