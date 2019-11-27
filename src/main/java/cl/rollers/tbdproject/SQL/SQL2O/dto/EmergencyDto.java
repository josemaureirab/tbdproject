package cl.rollers.tbdproject.SQL.SQL2O.dto;

import cl.rollers.tbdproject.SQL.SQL2O.models.Task;
import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryEmergency;
import lombok.Data;

@Data
public class EmergencyDto {
    private Integer id;
    private String name;
    private String description;
}