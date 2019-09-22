package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.VoluntaryDimension;
import cl.rollers.tbdproject.SQL.models.VoluntaryEmergency;
import lombok.Data;
import java.util.List;

@Data
public class VoluntaryDto {
    private Integer id;
    private String name;
    private String lastName;
    private String mail;
    private String gender;
    private String rut;
    private Integer age;
    private List<VoluntaryEmergency> voluntaryEmergencyList;
    private List<VoluntaryDimension> voluntaryDimensionList;
}