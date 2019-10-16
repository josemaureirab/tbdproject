package cl.rollers.tbdproject.SQL.dto;

import lombok.Data;

@Data
public class VoluntaryDto {
    private Integer id;
    private String name;
    private String lastName;
    private String mail;
    private String gender;
    private String rut;
    private Integer age;
    private Float latitude;
    private Float longitude;
    private List<VoluntaryEmergency> voluntaryEmergencyList;
    private List<VoluntaryDimension> voluntaryDimensionList;
}