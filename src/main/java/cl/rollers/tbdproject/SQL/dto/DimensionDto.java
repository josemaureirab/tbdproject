package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.VoluntaryDimension;
import lombok.Data;
import java.util.List;

@Data
public class DimensionDto {
    private Integer id;
    private String name;
    private Integer score;
}