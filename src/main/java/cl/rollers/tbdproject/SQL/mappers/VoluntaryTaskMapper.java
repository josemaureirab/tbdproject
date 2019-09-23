package cl.rollers.tbdproject.SQL.mappers;

import cl.rollers.tbdproject.SQL.dto.VoluntaryEmergencyDto;
import cl.rollers.tbdproject.SQL.dto.VoluntaryTaskDto;
import cl.rollers.tbdproject.SQL.models.VoluntaryEmergency;
import cl.rollers.tbdproject.SQL.models.VoluntaryTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VoluntaryTaskMapper {
    public VoluntaryTask mapToModel(VoluntaryTaskDto voluntaryTaskDto){
        VoluntaryTask voluntaryTask = new VoluntaryTask();
        voluntaryTask.setId(voluntaryTaskDto.getId());
        voluntaryTask.setVoluntary(voluntaryTaskDto.getVoluntary());
        voluntaryTask.setTask(voluntaryTaskDto.getTask());
        return voluntaryTask;
    }

    public VoluntaryTaskDto mapToDto(VoluntaryTask voluntaryTask){
        VoluntaryTaskDto voluntaryTaskDto = new VoluntaryTaskDto();
        voluntaryTaskDto.setId(voluntaryTask.getId());
        voluntaryTaskDto.setVoluntary(voluntaryTask.getVoluntary());
        voluntaryTaskDto.setTask(voluntaryTask.getTask());
        return voluntaryTaskDto;
    }

    public List<VoluntaryTaskDto> mapToDtoArrayList(List<VoluntaryTask> voluntaryTaskList) {
        int i;
        ArrayList<VoluntaryTaskDto> voluntaryTaskDtoArrayList = new ArrayList<>();
        for (i = 0; i < voluntaryTaskList.size(); i++) {
            voluntaryTaskDtoArrayList.add(mapToDto(voluntaryTaskList.get(i)));
        }
        return voluntaryTaskDtoArrayList;
    }
}