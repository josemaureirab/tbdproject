package cl.rollers.tbdproject.SQL.dto;

import cl.rollers.tbdproject.SQL.models.Task;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import lombok.Data;

@Data
public class VoluntaryTaskDto {
    private Integer id;
    private Voluntary voluntary;
    private Task task;
}