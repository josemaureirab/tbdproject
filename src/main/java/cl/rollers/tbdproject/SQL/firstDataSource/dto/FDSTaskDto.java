package cl.rollers.tbdproject.SQL.firstDataSource.dto;


import lombok.Data;

@Data
public class FDSTaskDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean status;
}
