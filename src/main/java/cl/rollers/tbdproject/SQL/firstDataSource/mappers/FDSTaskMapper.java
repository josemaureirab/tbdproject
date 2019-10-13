package cl.rollers.tbdproject.SQL.firstDataSource.mappers;


import cl.rollers.tbdproject.SQL.firstDataSource.dto.FDSTaskDto;
import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Component
public class FDSTaskMapper {

    public FDSTask mapToModel(FDSTaskDto taskDto){
        FDSTask task = new FDSTask();
        task.setId(taskDto.getId());
        task.setDescription(taskDto.getDescription());
        task.setName(taskDto.getName());
        task.setStatus(taskDto.getStatus());
        return task;
    }

    public List<FDSTaskDto> mapToDtoArrayList(List<FDSTask> tasks){
        int i;
        ArrayList<FDSTaskDto> usersDto = new ArrayList<>();
        for(i=0;i<tasks.size();i++){
            usersDto.add(mapToDto(tasks.get(i)));
        }

        return usersDto;
    }

    public FDSTaskDto mapToDto (FDSTask task){
        FDSTaskDto taskDto = new FDSTaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setName(task.getName());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }
}