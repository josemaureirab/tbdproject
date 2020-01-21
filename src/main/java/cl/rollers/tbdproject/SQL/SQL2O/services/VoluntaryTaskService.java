package cl.rollers.tbdproject.SQL.SQL2O.services;

import cl.rollers.tbdproject.SQL.SQL2O.dao.TaskDao;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.SQL2O.dao.VoluntaryTaskDao;
import cl.rollers.tbdproject.SQL.SQL2O.dto.VoluntaryTaskDto;
import cl.rollers.tbdproject.SQL.SQL2O.mappers.VoluntaryTaskMapper;
import cl.rollers.tbdproject.SQL.SQL2O.models.Task;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VoluntaryTaskService {
    @Autowired
    private VoluntaryDao voluntaryDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private VoluntaryTaskDao voluntaryTaskDao;

    @Autowired
    private VoluntaryTaskMapper voluntaryTaskMapper;


    public List<VoluntaryTaskDto> getAllVoluntaryTasks(){
        List<VoluntaryTask> voluntaryTaskList = voluntaryTaskDao.findAll();
        return voluntaryTaskMapper.mapToDtoArrayList(voluntaryTaskList);
    }

    public VoluntaryTaskDto createVoluntaryTask(VoluntaryTaskDto roleDto){
        return voluntaryTaskMapper.mapToDto(voluntaryTaskDao.save(voluntaryTaskMapper.mapToModel(roleDto)));
    }

    public VoluntaryTaskDto findVoluntaryTaskById(int id){
        if(voluntaryTaskDao.findById(id).isPresent()){
            return voluntaryTaskMapper.mapToDto(voluntaryTaskDao.findVoluntaryTaskById(id));
        }else{
            return null;
        }
    }

    public void updateVoluntaryTaskData(VoluntaryTaskDto roleDto, int id){
        VoluntaryTask voluntaryTaskFinded = voluntaryTaskDao.findVoluntaryTaskById(id);
        voluntaryTaskFinded.setVoluntary_id(roleDto.getVoluntary_id());
        voluntaryTaskFinded.setTask_id(roleDto.getTask_id());
        voluntaryTaskDao.save(voluntaryTaskFinded);
    }

    public void deleteVoluntaryTask(int id){
        voluntaryTaskDao.delete(voluntaryTaskDao.findVoluntaryTaskById(id));
    }

    public VoluntaryTaskDto createVoluntaryWithTask(Integer voluntaryId, Integer taskId) {
        Voluntary voluntary = voluntaryDao.findById(voluntaryId).get();
        Task task = taskDao.findById(taskId).get();
        VoluntaryTask voluntaryTask= new VoluntaryTask();
        voluntaryTask.setVoluntary_id(voluntary.getId());
        voluntaryTask.setTask_id(task.getId());
        return voluntaryTaskMapper.mapToDto(voluntaryTaskDao.save(voluntaryTask));
    }
}