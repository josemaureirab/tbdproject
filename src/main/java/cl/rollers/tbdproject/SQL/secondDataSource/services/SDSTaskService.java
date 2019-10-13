package cl.rollers.tbdproject.SQL.secondDataSource.services;


import cl.rollers.tbdproject.SQL.secondDataSource.dao.SDSTaskDao;
import cl.rollers.tbdproject.SQL.secondDataSource.dto.SDSTaskDto;
import cl.rollers.tbdproject.SQL.secondDataSource.mappers.SDSTaskMapper;
import cl.rollers.tbdproject.SQL.secondDataSource.models.SDSTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SDSTaskService {

    @Autowired
    private SDSTaskDao taskDao;

    @Autowired
    private SDSTaskMapper taskMapper;


    public List<SDSTaskDto> getAllSDSTasks(){
        ArrayList<SDSTask> tasks = taskDao.findAll();
        return taskMapper.mapToDtoArrayList(tasks);
    }

     public SDSTaskDto createSDSTask(SDSTaskDto taskDto){
        return taskMapper.mapToDto(taskDao.save(taskMapper.mapToModel(taskDto)));
     }

    public SDSTaskDto findSDSTaskById(int id){
        if(taskDao.findById(id).isPresent()){
            return taskMapper.mapToDto(taskDao.findTaskById(id));
        }
        else{
            return null;
        }

    }

    public void updateSDSTaskData(SDSTaskDto taskDto, int id){
        SDSTask taskFinded = taskDao.findTaskById(id);
        taskFinded.setName(taskDto.getName());
        taskFinded.setDescription(taskDto.getDescription());
        taskFinded.setStatus(taskDto.getStatus());
        taskDao.save(taskFinded);
    }

    public void deleteSDSTask(int id){
        SDSTask taskFinded = taskDao.findTaskById(id);
        taskDao.delete(taskFinded);
    }

}
