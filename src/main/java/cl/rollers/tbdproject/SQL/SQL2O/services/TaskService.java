package cl.rollers.tbdproject.SQL.SQL2O.services;


import cl.rollers.tbdproject.DB.SQL2O.DatabaseConnection;
import cl.rollers.tbdproject.SQL.SQL2O.dao.TaskDao;
import cl.rollers.tbdproject.SQL.SQL2O.dto.EmergencyDto;
import cl.rollers.tbdproject.SQL.SQL2O.dto.TaskDto;
import cl.rollers.tbdproject.SQL.SQL2O.mappers.TaskMapper;
import cl.rollers.tbdproject.SQL.SQL2O.models.Emergency;
import cl.rollers.tbdproject.SQL.SQL2O.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class TaskService {

    @Autowired
    DatabaseConnection databaseConnection;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskMapper taskMapper;


    public List<TaskDto> getAllTasks(){
        ArrayList<Task> tasks = taskDao.findAll();
        return taskMapper.mapToDtoArrayList(tasks);
    }

    public TaskDto createTask(TaskDto taskDto){
        return taskMapper.mapToDto(taskDao.save(taskMapper.mapToModel(taskDto)));
     }

     public TaskDto findTaskById(int id){ return findTaskByIdSql2o(id); }

    public void updateTask(TaskDto taskDto, int id) {
        try {
            updateTaskSql2o(taskDto, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int id) {
        try{
            deleteTaskSql2o(id);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /* SQL2O */
    private List<Task> findAll () {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
            List<Task> [] results = new ArrayList[databaseConnection.sql2o.length];
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                final int db = i;
                results[i] = new ArrayList<Task>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try(Connection conn = databaseConnection.sql2o[db].open()){
                            results[db] = conn.createQuery("select * from task")
                                    .executeAndFetch(Task.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24*3600, TimeUnit.SECONDS);
            List<Task> merged = new ArrayList<Task>();
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                merged.addAll(results[i]);
            }
            Collections.sort(merged);
            return merged;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TaskDto findTaskByIdSql2o (Integer id) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
            List<Task> [] results = new ArrayList[databaseConnection.sql2o.length];
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                final int db = i;
                results[i] = new ArrayList<Task>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try(Connection conn = databaseConnection.sql2o[db].open()){
                            final String query = "select * from task where id = :taskId";
                            results[db] = conn.createQuery(query)
                                    .addParameter("taskId", id)
                                    .executeAndFetch(Task.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24*3600, TimeUnit.SECONDS);
            List<Task> merged = new ArrayList<Task>();
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                merged.addAll(results[i]);
            }
            Collections.sort(merged);
            if (merged.size() != 0)
                return taskMapper.mapToDto(merged.get(0));
            else {
                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTaskSql2o (TaskDto taskDto, int id) {
        ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
        for( int i = 0; i < databaseConnection.sql2o.length; i++){
            final int db = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try(Connection conn = databaseConnection.sql2o[db].open()){
                        final String query = "UPDATE task SET name=:taskName, description=:taskDescription, status=:taskStatus  WHERE id = :taskId";
                        conn.createQuery(query)
                                .addParameter("taskId", id)
                                .addParameter("taskName", taskDto.getName())
                                .addParameter("taskDescription", taskDto.getDescription())
                                .addParameter("taskStatus", taskDto.getStatus())
                                .executeAndFetch(Task.class);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });
        }
        executor.shutdown();
    }

    public void deleteTaskSql2o (int id) {
        ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
        for( int i = 0; i < databaseConnection.sql2o.length; i++){
            final int db = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try(Connection conn = databaseConnection.sql2o[db].open()){
                        final String query = "DELETE FROM task WHERE id = :taskId";
                        conn.createQuery(query)
                                .addParameter("taskId", id)
                                .executeAndFetch(Task.class);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });
        }
        executor.shutdown();
    }





}
