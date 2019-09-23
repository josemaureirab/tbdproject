package cl.rollers.tbdproject;

import cl.rollers.tbdproject.SQL.dao.*;
import cl.rollers.tbdproject.SQL.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Order(1)
public class DBSeeder implements CommandLineRunner {

    @Autowired
    private DimensionDao dimensionDao;

    @Autowired
    private EmergencyDao emergencyDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private VoluntaryDao voluntaryDao;

    @Autowired
    private VoluntaryDimensionDao voluntaryDimensionDao;

    @Autowired
    private VoluntaryEmergencyDao voluntaryEmergencyDao;

    public void seedDimensions(){
        dimensionDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            Dimension dimension = new Dimension();
            dimension.setName("dimension "+i);
            dimension.setScore(i);
            dimension.setVoluntaryDimensionList(new ArrayList<>());
            dimensionDao.save(dimension);
        }
    }

    public void seedEmergencies(){
        emergencyDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            Emergency emergency = new Emergency();
            emergency.setName("emergency "+i);
            emergency.setDescription("descripción "+i);
            emergency.setTaskList(new ArrayList<>());
            emergency.setVoluntaryEmergencyList(new ArrayList<>());
            emergencyDao.save(emergency);
        }
    }

    public void seedRoles(){
        roleDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            Role role = new Role();
            role.setName("role "+i);
            roleDao.save(role);
        }
    }

    public void seedTasks(){
        taskDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setName("task "+i);
            task.setDescription("descripción "+i);
            task.setStatus(true);
            task.setEmergency(null);
            taskDao.save(task);
        }
    }

    public void seedVoluntaries(){
        voluntaryDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            Voluntary voluntary = new Voluntary();
            voluntary.setName("voluntary "+i);
            voluntary.setLastName("descripción "+i);
            voluntary.setAge(i);
            voluntary.setRut("rut");
            voluntary.setVoluntaryEmergencyList(new ArrayList<>());
            voluntary.setVoluntaryDimensionList(new ArrayList<>());
            voluntaryDao.save(voluntary);
        }
    }

    public void seedVoluntaryDimensions(){
        voluntaryDimensionDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            VoluntaryDimension voluntaryDimension = new VoluntaryDimension();
            voluntaryDimension.setVoluntary(null);
            voluntaryDimension.setDimension(null);
            voluntaryDimensionDao.save(voluntaryDimension);
        }
    }

    public void seedVoluntaryEmergencies(){
        voluntaryEmergencyDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            VoluntaryEmergency voluntaryEmergency = new VoluntaryEmergency();
            voluntaryEmergency.setVoluntary(null);
            voluntaryEmergency.setEmergency(null);
            voluntaryEmergencyDao.save(voluntaryEmergency);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        seedDimensions();
        seedEmergencies();
        seedRoles();
        seedTasks();
        //seedVoluntaryDimensions();
        //seedVoluntaryEmergencies();
    }
}