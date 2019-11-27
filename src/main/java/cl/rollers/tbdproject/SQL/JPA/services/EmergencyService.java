/*
package cl.rollers.tbdproject.SQL.JPA.services;

import cl.rollers.tbdproject.SQL.JPA.dao.EmergencyDao;
import cl.rollers.tbdproject.SQL.JPA.dao.TaskDao;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryEmergencyDao;
import cl.rollers.tbdproject.SQL.JPA.dto.EmergencyDto;
import cl.rollers.tbdproject.SQL.JPA.dto.TaskDto;
import cl.rollers.tbdproject.SQL.JPA.mappers.EmergencyMapper;
import cl.rollers.tbdproject.SQL.JPA.mappers.TaskMapper;
import cl.rollers.tbdproject.SQL.JPA.models.Emergency;
import cl.rollers.tbdproject.SQL.JPA.models.Task;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.JPA.models.VoluntaryEmergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmergencyService {

    @Autowired
    private EmergencyDao emergencyDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private VoluntaryDao voluntaryDao;

    @Autowired
    private VoluntaryEmergencyDao voluntaryEmergencyDao;

    @Autowired
    private EmergencyMapper emergencyMapper;

    @Autowired
    private TaskMapper taskMapper;


    public List<EmergencyDto> getAllEmergencies(){
        ArrayList<Emergency> emergencies = emergencyDao.findAll();
        return emergencyMapper.mapToDtoArrayList(emergencies);
    }

    public EmergencyDto createEmergency(EmergencyDto emergencyDto){
        return emergencyMapper.mapToDto(emergencyDao.save(emergencyMapper.mapToModel(emergencyDto)));
    }

    public EmergencyDto findEmergencyById(int id){
        if(emergencyDao.findById(id).isPresent()){
            return emergencyMapper.mapToDto(emergencyDao.findEmergencyById(id));
        }
        else{
            return null;
        }
    }

    public void updateEmergency(EmergencyDto emergencyDto, int id){
        Emergency emergencyFinded = emergencyDao.findEmergencyById(id);
        emergencyFinded.setName(emergencyDto.getName());
        emergencyFinded.setDescription(emergencyDto.getDescription());
        emergencyDao.save(emergencyFinded);
    }

    public void deleteEmergency(int id){
        Emergency emergencyFinded = emergencyDao.findEmergencyById(id);
        emergencyDao.delete(emergencyFinded);
    }

    public TaskDto appendTask(Integer emergencyId, Integer taskId){
        Emergency emergency = emergencyDao.findEmergencyById(emergencyId);
        Task task = taskDao.findTaskById(taskId);
        task.setEmergency_id(emergency.getId());
        return taskMapper.mapToDto(taskDao.save(task));
    }

    private Task findTaskInEmergency(List<Task> taskList, Task task){
        for (Task taskInEmergency: taskList) {
            if(taskInEmergency.equals(task)){
                return task;
            }
        }
        return null;
    }

    public EmergencyDto appendVoluntary(Integer emergencyId, Integer voluntaryId){
        Emergency emergency = emergencyDao.findEmergencyById(emergencyId);
        Voluntary voluntary = voluntaryDao.findVoluntaryById(voluntaryId);
        try{
            VoluntaryEmergency voluntaryEmergency = voluntaryEmergencyDao.findVoluntaryEmergencyByEmergencyAndVoluntary(emergency.getId(), voluntary.getId());
            if(voluntaryEmergency == null) {
                voluntaryEmergency = new VoluntaryEmergency();
                voluntaryEmergency.setEmergency(emergency.getId());
                voluntaryEmergency.setVoluntary(voluntary.getId());
                emergencyDao.save(emergency);
                return emergencyMapper.mapToDto(emergency);
            }
            else {
                return emergencyMapper.mapToDto(emergency);
            }
        }catch (Exception e){
            return null;
        }
    }

    private Voluntary findVoluntaryInEmergency(List<VoluntaryEmergency> voluntaryEmergencyList, Voluntary voluntary){
        for (VoluntaryEmergency voluntaryInEmergency: voluntaryEmergencyList) {
            if(voluntaryInEmergency.getVoluntary().equals(voluntary.getId())){
                return voluntary;
            }
        }
        return null;
    }
}
*/
