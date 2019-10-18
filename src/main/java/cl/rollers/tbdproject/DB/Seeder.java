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
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;

/*@Component
@Order(1)*/
public class Seeder implements CommandLineRunner {

    private Sql2o sql2o[];

    public Seeder(Sql2o[] sql2o) {
        this.sql2o = sql2o;
    }

    @Autowired
    private TaskDao taskDao;

    public void seedEmergencies() {
        for (int i = 0; i < 10; i++) {
            int db = 0;
            try(Connection conn = sql2o[db].open()){
                conn.createQuery(
                        "insert into emergencies(id, name, description) values (:id, :name, :description)")
                        .addParameter("id", i + 11)
                        .addParameter("name", "task "+i)
                        .addParameter("description", "descripción "+i)
                        .executeUpdate();
            }
            db = 1;
            try(Connection conn = sql2o[db].open()){
                conn.createQuery(
                        "insert into emergencies(id, name, description) values (:id, :name, :description)")
                        .addParameter("id", i + 11)
                        .addParameter("name", "task "+i)
                        .addParameter("description", "descripción "+i)
                        .executeUpdate();
            }
        }
    }

    public void seedTasks() {
        for (int i = 0; i < 10; i++) {
            int db = 0;
            try(Connection conn = sql2o[db].open()){
                conn.createQuery(
                    "insert into task(id, name, description, status, emergency_id) values (:id, :name, :description, :status, :emergency_id)")
                    .addParameter("id", i + 11)
                    .addParameter("name", "task "+i)
                    .addParameter("description", "descripción "+i)
                    .addParameter("status", true)
                    .addParameter("emergency_id", 1)
                    .executeUpdate();
            }
            db = 1;
            try(Connection conn = sql2o[db].open()){
                conn.createQuery(
                    "insert into task(id, name, description, status, emergency_id) values (:id, :name, :description, :status, :emergency_id)")
                    .addParameter("id", i + 11)
                    .addParameter("name", "task "+i)
                    .addParameter("description", "descripción "+i)
                    .addParameter("status", true)
                    .addParameter("emergency_id", 1)
                    .executeUpdate();
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        seedEmergencies();
        seedTasks();
    }
}