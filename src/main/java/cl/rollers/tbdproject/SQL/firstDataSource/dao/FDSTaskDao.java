package cl.rollers.tbdproject.SQL.firstDataSource.dao;

import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FDSTaskDao extends JpaRepository<FDSTask, Integer> {
    ArrayList<FDSTask> findAll();
    FDSTask findFDSTaskById(Integer id);
}
