package cl.rollers.tbdproject.SQL.SQL2O.dto;

import cl.rollers.tbdproject.SQL.SQL2O.models.Dimension;
import cl.rollers.tbdproject.SQL.SQL2O.models.Voluntary;
import lombok.Data;

@Data
public class VoluntaryDimensionDto {
    private Integer id;
    private Integer voluntary_id;
    private Integer dimension_id;
}