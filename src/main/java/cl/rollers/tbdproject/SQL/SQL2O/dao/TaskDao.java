package cl.rollers.tbdproject.SQL.SQL2O.dao;

import cl.rollers.tbdproject.SQL.SQL2O.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TaskDao extends JpaRepository<Task, Integer> {
    ArrayList<Task> findAll();
    Task findTaskById(Integer id);
}
