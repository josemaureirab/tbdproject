package cl.rollers.tbdproject.SQL.mappers;


import cl.rollers.tbdproject.SQL.dto.TaskDto;
import cl.rollers.tbdproject.SQL.models.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Component
public class TaskMapper {

    public Task mapToModel(TaskDto taskDto){
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setDescription(taskDto.getDescription());
        task.setName(taskDto.getName());
        task.setStatus(taskDto.getStatus());
        return task;
    }

    public List<TaskDto> mapToDtoArrayList(List<Task> tasks){
        int i;
        ArrayList<TaskDto> usersDto = new ArrayList<>();
        for(i=0;i<tasks.size();i++){
            usersDto.add(mapToDto(tasks.get(i)));
        }

        return usersDto;
    }

    public TaskDto mapToDto (Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setName(task.getName());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }
}