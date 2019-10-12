package cl.rollers.tbdproject.DB;

import cl.rollers.tbdproject.SQL.dao.*;
import cl.rollers.tbdproject.SQL.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Seeder implements CommandLineRunner {

    @Autowired
    private EntityManagerUtils emUtils;

    @Autowired
    private TaskDao taskDao;

    public void seedTasks() {
        taskDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setName("task "+i);
            task.setDescription("descripción "+i);
            task.setStatus(true);
            taskDao.save(task);
        }
    }

    private void setRepository(String url){
        taskDao = emUtils.getJpaFactory(url).getRepository(TaskDao.class);
    }

    @Override
    public void run(String... args) throws Exception {
        setRepository("first");
        seedTasks();
        setRepository("second");
        seedTasks();
    }
}