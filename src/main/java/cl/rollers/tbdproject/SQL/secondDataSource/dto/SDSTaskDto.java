package cl.rollers.tbdproject.SQL.secondDataSource.dto;


import lombok.Data;

@Data
public class SDSTaskDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;
}
