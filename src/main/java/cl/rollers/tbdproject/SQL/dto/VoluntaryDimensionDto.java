package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.Dimension;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import lombok.Data;

@Data
public class VoluntaryDimensionDto {
    private Integer id;
    private Voluntary voluntary;
    private Dimension dimension;
}