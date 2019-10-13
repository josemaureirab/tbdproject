package cl.rollers.tbdproject.SQL.secondDataSource.mappers;


import cl.rollers.tbdproject.SQL.secondDataSource.dto.SDSTaskDto;
import cl.rollers.tbdproject.SQL.secondDataSource.models.SDSTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Component
public class SDSTaskMapper {

    public SDSTask mapToModel(SDSTaskDto taskDto){
        SDSTask task = new SDSTask();
        task.setId(taskDto.getId());
        task.setDescription(taskDto.getDescription());
        task.setName(taskDto.getName());
        task.setStatus(taskDto.getStatus());
        return task;
    }

    public List<SDSTaskDto> mapToDtoArrayList(List<SDSTask> tasks){
        int i;
        ArrayList<SDSTaskDto> usersDto = new ArrayList<>();
        for(i=0;i<tasks.size();i++){
            usersDto.add(mapToDto(tasks.get(i)));
        }

        return usersDto;
    }

    public SDSTaskDto mapToDto (SDSTask task){
        SDSTaskDto taskDto = new SDSTaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setName(task.getName());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }
}