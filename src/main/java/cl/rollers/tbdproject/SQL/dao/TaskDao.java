package cl.rollers.tbdproject.SQL.dao;

import cl.rollers.tbdproject.SQL.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TaskDao extends JpaRepository<Task, Integer> {
    ArrayList<Task> findAll();
    Task findTaskById(Integer id);
}
