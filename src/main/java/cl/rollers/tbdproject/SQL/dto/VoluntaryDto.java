package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.VoluntaryEmergency;
import lombok.Data;
import java.util.List;

@Data
public class VoluntaryDto {
    private Integer id;
    private String name;
    private String lastName;
    private String rut;
    private Integer age;
    private List<VoluntaryEmergency> voluntaryEmergencyList;
}