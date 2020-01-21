package cl.rollers.tbdproject.SQL.SQL2O.dto;

import cl.rollers.tbdproject.SQL.SQL2O.models.Task;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import lombok.Data;

@Data
public class VoluntaryTaskDto {
    private Integer id;
    private Integer voluntary_id;
    private Integer task_id;
}