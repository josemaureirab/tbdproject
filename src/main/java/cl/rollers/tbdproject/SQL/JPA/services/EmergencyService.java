
package cl.rollers.tbdproject.SQL.JPA.services;

import cl.rollers.tbdproject.SQL.JPA.dao.EmergencyDao;
//import cl.rollers.tbdproject.SQL.JPA.dao.TaskDao;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
//import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryEmergencyDao;
import cl.rollers.tbdproject.SQL.JPA.dto.EmergencyDto;
//import cl.rollers.tbdproject.SQL.JPA.dto.TaskDto;
import cl.rollers.tbdproject.SQL.JPA.mappers.EmergencyMapper;
//import cl.rollers.tbdproject.SQL.JPA.mappers.TaskMapper;
import cl.rollers.tbdproject.SQL.JPA.models.Emergency;
//import cl.rollers.tbdproject.SQL.JPA.models.Task;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
//import cl.rollers.tbdproject.SQL.JPA.models.VoluntaryEmergency;
import cl.rollers.tbdproject.SQL.SQL2O.dao.TaskDao;
import cl.rollers.tbdproject.SQL.SQL2O.dao.VoluntaryEmergencyDao;
import cl.rollers.tbdproject.SQL.SQL2O.dto.TaskDto;
import cl.rollers.tbdproject.SQL.SQL2O.features.Feature;
import cl.rollers.tbdproject.SQL.SQL2O.mappers.TaskMapper;
import cl.rollers.tbdproject.SQL.SQL2O.models.Task;
import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryEmergency;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

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


    public ArrayList<Feature> getAllEmergencies(){
        ArrayList<Emergency> emergencies = emergencyDao.findAll();
        ArrayList<Feature> featureCollection = new ArrayList<>();
        if(emergencies.isEmpty()){
            return featureCollection;
        }

        for (Emergency emergency : emergencies) {
            HashMap<String, Object> properties = new HashMap<>();
            properties.put("name", emergency.getName());
            properties.put("description", emergency.getDescription());
            Feature feature = new Feature(emergency.getLocation(), properties);
            featureCollection.add(feature);
        }
        return featureCollection;
    }

    public Feature createEmergency(Feature feature){
        ArrayList<Object> data = new ArrayList<>();
        Emergency emergency = new Emergency();
        Map<String, Object> properties = feature.getProperties();
        System.out.println(feature.getProperties().toString());
        Set<Map.Entry<String, Object>> entrySet = properties.entrySet();
        for(Map.Entry<String, Object> entry : entrySet){
            data.add(entry.getValue());
        }
        System.out.println("data");
        System.out.println(data.toString());
        emergency.setName(data.get(0).toString());
        emergency.setDescription(data.get(1).toString());
        emergency.setLocation((Point) feature.getGeometry());
        emergencyDao.save(emergency);
        return feature;
    }

    public Feature findEmergencyById(int id){
        if(emergencyDao.findById(id).isPresent()){
            Emergency emergency = emergencyDao.findById(id).get();
            HashMap<String, Object> properties = new HashMap<>();
            properties.put("name", emergency.getName());
            properties.put("description", emergency.getDescription());
            Feature feature = new Feature(emergency.getLocation(), properties);
            return feature;
        }
        else{
            return null;
        }
    }

    public void updateEmergency(Feature feature, int id){
        Emergency emergencyFinded = emergencyDao.findEmergencyById(id);
        ArrayList<Object> data = new ArrayList<>();
        Map<String, Object> properties = feature.getProperties();
        System.out.println(feature.getProperties().toString());
        Set<Map.Entry<String, Object>> entrySet = properties.entrySet();
        for(Map.Entry<String, Object> entry : entrySet){
            data.add(entry.getValue());
        }
        System.out.println("data");
        System.out.println(data.toString());
        emergencyFinded.setName(data.get(0).toString());
        emergencyFinded.setDescription(data.get(1).toString());
        emergencyFinded.setLocation((Point) feature.getGeometry());
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

