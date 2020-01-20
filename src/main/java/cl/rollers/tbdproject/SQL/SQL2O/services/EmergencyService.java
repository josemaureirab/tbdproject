package cl.rollers.tbdproject.SQL.SQL2O.services;

import cl.rollers.tbdproject.DB.SQL2O.DatabaseConnection;
import cl.rollers.tbdproject.SQL.SQL2O.dao.EmergencyDao;
import cl.rollers.tbdproject.SQL.SQL2O.dao.TaskDao;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.SQL2O.dao.VoluntaryEmergencyDao;
import cl.rollers.tbdproject.SQL.SQL2O.dto.EmergencyDto;
import cl.rollers.tbdproject.SQL.SQL2O.dto.TaskDto;
import cl.rollers.tbdproject.SQL.SQL2O.mappers.EmergencyMapper;
import cl.rollers.tbdproject.SQL.SQL2O.mappers.TaskMapper;
import cl.rollers.tbdproject.SQL.SQL2O.models.Emergency;
import cl.rollers.tbdproject.SQL.SQL2O.models.Task;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryEmergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class EmergencyService {

    @Autowired
    DatabaseConnection databaseConnection;
    
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
        List<Emergency> emergencies = findAll();
        return emergencyMapper.mapToDtoArrayList(emergencies);
    }

    public EmergencyDto createEmergency(EmergencyDto emergencyDto){
        return emergencyMapper.mapToDto(emergencyDao.save(emergencyMapper.mapToModel(emergencyDto)));
    }

    public EmergencyDto findEmergencyById(int id){
        return findEmergencyByIdSql2o(id);
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
    
    /* SQL2O */
    private List<Emergency> findAll () {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
            List<Emergency> [] results = new ArrayList[databaseConnection.sql2o.length];
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                final int db = i;
                results[i] = new ArrayList<Emergency>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try(Connection conn = databaseConnection.sql2o[db].open()){
                            results[db] = conn.createQuery("select * from emergency")
                                .executeAndFetch(Emergency.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24*3600, TimeUnit.SECONDS);
            List<Emergency> merged = new ArrayList<Emergency>();
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                merged.addAll(results[i]);
            }
            Collections.sort(merged);
            return merged;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private EmergencyDto findEmergencyByIdSql2o (Integer id) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
            List<Emergency> [] results = new ArrayList[databaseConnection.sql2o.length];
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                final int db = i;
                results[i] = new ArrayList<Emergency>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try(Connection conn = databaseConnection.sql2o[db].open()){
                            final String query = "select * from emergency where id = :emergencyId";
                            results[db] = conn.createQuery(query)
                                    .addParameter("emergencyId", id)
                                    .executeAndFetch(Emergency.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24*3600, TimeUnit.SECONDS);
            List<Emergency> merged = new ArrayList<Emergency>();
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                merged.addAll(results[i]);
            }
            Collections.sort(merged);
            if (merged.size() != 0)
                return emergencyMapper.mapToDto(merged.get(0));
            else {
                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
