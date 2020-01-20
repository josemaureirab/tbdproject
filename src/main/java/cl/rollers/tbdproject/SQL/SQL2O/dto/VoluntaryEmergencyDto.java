package cl.rollers.tbdproject.SQL.SQL2O.dto;

import cl.rollers.tbdproject.SQL.SQL2O.models.Emergency;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import lombok.Data;

@Data
public class VoluntaryEmergencyDto {
    private Integer id;
    private Integer voluntary_id;
    private Integer emergency_id;
}