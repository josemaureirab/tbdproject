package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.Dimension;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import lombok.Data;

@Data
public class VoluntaryDimensionDto {
    private Integer id;
    private Integer voluntary_id;
    private Integer dimension_id;
}