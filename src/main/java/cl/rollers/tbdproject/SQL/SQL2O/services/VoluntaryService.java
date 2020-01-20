package cl.rollers.tbdproject.SQL.SQL2O.services;

import cl.rollers.tbdproject.DB.SQL2O.DatabaseConnection;
import cl.rollers.tbdproject.SQL.SQL2O.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.SQL2O.dto.EmergencyDto;
import cl.rollers.tbdproject.SQL.SQL2O.mappers.VoluntaryMapper;
import cl.rollers.tbdproject.SQL.SQL2O.models.Emergency;
import cl.rollers.tbdproject.SQL.SQL2O.models.Voluntary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.rollers.tbdproject.SQL.SQL2O.dto.VoluntaryDto;
import org.sql2o.Connection;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class VoluntaryService {
    @Autowired
    private VoluntaryMapper voluntaryMapper;

    @Autowired
    private VoluntaryDao voluntaryDao;

    @Autowired
    DatabaseConnection databaseConnection;

    public VoluntaryDto createVoluntary(VoluntaryDto voluntaryDto){
        return voluntaryMapper.mapToDto(voluntaryDao.save(voluntaryMapper.mapToModel(voluntaryDto)));
    }

    public List<VoluntaryDto> getAllVoluntaries(){
        List<Voluntary> voluntaryList = findAll();
        return voluntaryMapper.mapToDtoArrayList(voluntaryList);
    }

    public VoluntaryDto findVoluntaryById(Integer id){
        return findVoluntaryByIdSql2o(id);

    }

    public void updateVoluntaryData(VoluntaryDto voluntaryDto, Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryFinded.setName(voluntaryDto.getName());
        voluntaryFinded.setLastName(voluntaryDto.getLastName());
        voluntaryFinded.setRut(voluntaryDto.getLastName());
        voluntaryFinded.setMail(voluntaryDto.getMail());
        voluntaryFinded.setGender(voluntaryDto.getGender());
        voluntaryFinded.setAge(voluntaryDto.getAge());
        /*voluntaryFinded.setLatitude(voluntaryDto.getLatitude());
        voluntaryFinded.setLongitude(voluntaryDto.getLongitude());*/
        voluntaryDao.save(voluntaryFinded);
    }

    public void deleteVoluntary(Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryDao.delete(voluntaryFinded);
    }

    /* SQL2O */
    private List<Voluntary> findAll () {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
            List<Voluntary> [] results = new ArrayList[databaseConnection.sql2o.length];
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                final int db = i;
                results[i] = new ArrayList<Voluntary>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try(Connection conn = databaseConnection.sql2o[db].open()){
                            results[db] = conn.createQuery("select * from voluntary")
                                    .executeAndFetch(Voluntary.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24*3600, TimeUnit.SECONDS);
            List<Voluntary> merged = new ArrayList<Voluntary>();
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

    private VoluntaryDto findVoluntaryByIdSql2o (Integer id) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
            List<Voluntary> [] results = new ArrayList[databaseConnection.sql2o.length];
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                final int db = i;
                results[i] = new ArrayList<Voluntary>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try(Connection conn = databaseConnection.sql2o[db].open()){
                            final String query = "select * from voluntary where id = :voluntaryId";
                            results[db] = conn.createQuery(query)
                                    .addParameter("voluntaryId", id)
                                    .executeAndFetch(Voluntary.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24*3600, TimeUnit.SECONDS);
            List<Voluntary> merged = new ArrayList<Voluntary>();
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                merged.addAll(results[i]);
            }
            Collections.sort(merged);
            if (merged.size() != 0)
                return voluntaryMapper.mapToDto(merged.get(0));
            else {
                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

 }