package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.Task;
import cl.rollers.tbdproject.SQL.models.VoluntaryEmergency;
import lombok.Data;
import java.util.List;

@Data
public class EmergencyDto {
    private Integer id;
    private String name;
    private String description;
}