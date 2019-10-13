package cl.rollers.tbdproject.DB;

import cl.rollers.tbdproject.SQL.firstDataSource.dao.FDSTaskDao;
import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSTask;
import cl.rollers.tbdproject.SQL.secondDataSource.dao.SDSTaskDao;
import cl.rollers.tbdproject.SQL.secondDataSource.models.SDSTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Seeder implements CommandLineRunner {

    @Autowired
    private FDSTaskDao FDStaskDao;

    @Autowired
    private SDSTaskDao SDStaskDao;

    public void seedTasks() {
        FDStaskDao.deleteAll();
        for (int i = 0; i < 10; i++) {
            FDSTask task = new FDSTask();
            task.setName("task "+i);
            task.setDescription("descripción "+i);
            task.setStatus(true);
            FDStaskDao.save(task);
        }

        SDStaskDao.deleteAll();
        for (int i = 10; i < 20; i++) {
            SDSTask task = new SDSTask();
            task.setName("task "+i);
            task.setDescription("descripción "+i);
            task.setStatus(true);
            SDStaskDao.save(task);
        }
    }
    @Override
    public void run(String... args) throws Exception {
        seedTasks();
    }
}