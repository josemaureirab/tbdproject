package cl.rollers.tbdproject.SQL.firstDataSource.services;


import cl.rollers.tbdproject.SQL.firstDataSource.dao.FDSTaskDao;
import cl.rollers.tbdproject.SQL.firstDataSource.dto.FDSTaskDto;
import cl.rollers.tbdproject.SQL.firstDataSource.mappers.FDSTaskMapper;
import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FDSTaskService {

    @Autowired
    private FDSTaskDao taskDao;

    @Autowired
    private FDSTaskMapper taskMapper;


    public List<FDSTaskDto> getAllFDSTasks(){
        ArrayList<FDSTask> tasks = taskDao.findAll();
        return taskMapper.mapToDtoArrayList(tasks);
    }

     public FDSTaskDto createFDSTask(FDSTaskDto taskDto){
        return taskMapper.mapToDto(taskDao.save(taskMapper.mapToModel(taskDto)));
     }

    public FDSTaskDto findFDSTaskById(int id){
        if(taskDao.findById(id).isPresent()){
            return taskMapper.mapToDto(taskDao.findFDSTaskById(id));
        }
        else{
            return null;
        }

    }

    public void updateFDSTaskData(FDSTaskDto taskDto, int id){
        FDSTask taskFinded = taskDao.findFDSTaskById(id);
        taskFinded.setName(taskDto.getName());
        taskFinded.setDescription(taskDto.getDescription());
        taskFinded.setStatus(taskDto.getStatus());
        taskDao.save(taskFinded);
    }

    public void deleteFDSTask(int id){
        FDSTask taskFinded = taskDao.findFDSTaskById(id);
        taskDao.delete(taskFinded);
    }

}
