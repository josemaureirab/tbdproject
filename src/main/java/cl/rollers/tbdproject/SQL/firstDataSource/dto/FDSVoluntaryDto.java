package cl.rollers.tbdproject.SQL.firstDataSource.dto;

import lombok.Data;

@Data
public class FDSVoluntaryDto {
    private Integer id;
    private String name;
    private String lastName;
    private String mail;
    private String gender;
    private String rut;
    private Integer age;
}