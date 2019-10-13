package cl.rollers.tbdproject.SQL.secondDataSource.dao;

import cl.rollers.tbdproject.SQL.secondDataSource.models.SDSTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface SDSTaskDao extends JpaRepository<SDSTask, Integer> {
    ArrayList<SDSTask> findAll();
    SDSTask findTaskById(Integer id);
}
