package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.Emergency;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import lombok.Data;

@Data
public class VoluntaryEmergencyDto {
    private Integer id;
    private Voluntary voluntary;
    private Emergency emergency;
}