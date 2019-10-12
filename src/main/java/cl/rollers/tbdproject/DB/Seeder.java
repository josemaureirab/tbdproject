package cl.rollers.tbdproject.DB;

import cl.rollers.tbdproject.SQL.dao.*;
import cl.rollers.tbdproject.SQL.models.*;
import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;

@Component
@Order(1)
public class Seeder implements CommandLineRunner {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private DataSource dataSource;

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

    @Override
    public void run(String... args) throws Exception {

        seedTasks();

        seedTasks();
    }
}