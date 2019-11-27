package cl.rollers.tbdproject.DB.JPA;

import cl.rollers.tbdproject.SQL.JPA.dao.*;
import cl.rollers.tbdproject.SQL.JPA.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(0)
public class DatabaseSeeder implements CommandLineRunner {
    
    @Autowired
    UserDao users;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    /*@Autowired
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
            dimensionDao.save(dimension);
        }
    }

    public void seedEmergencies(){
        emergencyDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            Emergency emergency = new Emergency();
            emergency.setName("emergency "+i);
            emergency.setDescription("descripción "+i);
            emergencyDao.save(emergency);
        }
    }

    public void seedTasks(){
        taskDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setName("task "+i);
            task.setDescription("descripción "+i);
            task.setStatus(true);
            task.setEmergency_id(null);
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
            voluntaryDao.save(voluntary);
        }
    }

    public void seedVoluntaryDimensions(){
        voluntaryDimensionDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            VoluntaryDimension voluntaryDimension = new VoluntaryDimension();
            voluntaryDimension.setVoluntary_id(null);
            voluntaryDimension.setDimension_id(null);
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
    }*/

    @Override
    public void run(String... args) throws Exception {
        /*seedDimensions();
        seedEmergencies();
        seedTasks();
        seedVoluntaryDimensions();
        seedVoluntaryEmergencies();*/
        List<User> userList = users.findAll();
        if(userList.size() > 0){
            return;
        }
        else {
            this.users.save(User.builder()
                .username("user")
                .password(this.passwordEncoder.encode("secret"))
                .roles(Arrays.asList("ROLE_USER"))
                .build()
            );
            this.users.save(User.builder()
                .username("admin")
                .password(this.passwordEncoder.encode("secret"))
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build()
            );
        }
    }
}