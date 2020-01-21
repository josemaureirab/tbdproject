package cl.rollers.tbdproject.SQL.SQL2O.services;

import cl.rollers.tbdproject.SQL.SQL2O.dao.DimensionDao;
import cl.rollers.tbdproject.SQL.SQL2O.dto.DimensionDto;
import cl.rollers.tbdproject.SQL.SQL2O.dto.EmergencyDto;
import cl.rollers.tbdproject.SQL.SQL2O.mappers.DimensionMapper;
import cl.rollers.tbdproject.SQL.SQL2O.models.Dimension;
import cl.rollers.tbdproject.SQL.SQL2O.models.Emergency;
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
public class DimensionService {
    @Autowired
    private DimensionDao dimensionDao;

    @Autowired
    private DimensionMapper dimensionMapper;


    public List<DimensionDto> getAllDimensions(){
        List<Dimension> dimensionList = dimensionDao.findAll();
        return dimensionMapper.mapToDtoArrayList(dimensionList);
    }

    public DimensionDto createDimension(DimensionDto dimensionDto){
        return dimensionMapper.mapToDto(dimensionDao.save(dimensionMapper.mapToModel(dimensionDto)));
    }

    /*

    public DimensionDto findDimensionById(int id){
        if(dimensionDao.findById(id).isPresent()){
            return dimensionMapper.mapToDto(dimensionDao.findDimensionById(id));
        }else{
            return null;
        }
    }
    */

    public DimensionDto findDimensionById(int id){ return findDimensionByIdSql2o(id); }

    public void updateDimensionData(DimensionDto dimensionDto, int id){
        Dimension dimensionFinded = dimensionMapper.mapToModel(findDimensionByIdSql2o(id));
        dimensionFinded.setName(dimensionDto.getName());
        dimensionFinded.setScore(dimensionDto.getScore());
        dimensionDao.save(dimensionFinded);
    }

    public void deleteDimension(int id){
        dimensionDao.delete(dimensionDao.findDimensionById(id));
    }

    /* SQL2O */
    private List<Dimension> findAll () {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
            List<Dimension> [] results = new ArrayList[databaseConnection.sql2o.length];
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                final int db = i;
                results[i] = new ArrayList<Dimension>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try(Connection conn = databaseConnection.sql2o[db].open()){
                            results[db] = conn.createQuery("select * from dimension")
                                    .executeAndFetch(Dimension.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24*3600, TimeUnit.SECONDS);
            List<Dimension> merged = new ArrayList<Dimension>();
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

    private DimensionDto findDimensionByIdSql2o (Integer id) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(databaseConnection.sql2o.length);
            List<Dimension> [] results = new ArrayList[databaseConnection.sql2o.length];
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                final int db = i;
                results[i] = new ArrayList<Dimension>();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try(Connection conn = databaseConnection.sql2o[db].open()){
                            final String query = "select * from dimension where id = :dimensionId";
                            results[db] = conn.createQuery(query)
                                    .addParameter("dimensionId", id)
                                    .executeAndFetch(Dimension.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24*3600, TimeUnit.SECONDS);
            List<Dimension> merged = new ArrayList<Dimension>();
            for( int i = 0; i < databaseConnection.sql2o.length; i++){
                merged.addAll(results[i]);
            }
            Collections.sort(merged);
            if (merged.size() != 0)
                return dimensionMapper.mapToDto(merged.get(0));
            else {
                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}