package cl.rollers.tbdproject.SQL.services;


import cl.rollers.tbdproject.SQL.dao.TaskDao;
import cl.rollers.tbdproject.SQL.dto.TaskDto;
import cl.rollers.tbdproject.SQL.mappers.TaskMapper;
import cl.rollers.tbdproject.SQL.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskMapper taskMapper;


    public List<TaskDto> getAllTasks(){
        ArrayList<Task> tasks = taskDao.findAll();
        return taskMapper.mapToDtoArrayList(tasks);
    }

     public TaskDto createTask(TaskDto taskDto){
        return taskMapper.mapToDto(taskDao.save(taskMapper.mapToModel(taskDto)));
     }

    public TaskDto findTaskById(int id){
        if(taskDao.findById(id).isPresent()){
            return taskMapper.mapToDto(taskDao.findTaskById(id));
        }
        else{
            return null;
        }

    }

    public void updateTaskData(TaskDto guestDto, int id){
        Task taskFinded = taskDao.findTaskById(id);
        taskFinded.setName(guestDto.getName());
        taskFinded.setDescription(guestDto.getDescription());
        taskFinded.setStatus(guestDto.getStatus());
        taskDao.save(taskFinded);
    }

    public void deleteTask(int id){
        Task guestFinded = taskDao.findTaskById(id);
        taskDao.delete(guestFinded);
    }

}
