package cl.rollers.tbdproject.SQL.JPA.services;

import cl.rollers.tbdproject.DB.SQL2O.DatabaseConnection;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.JPA.dto.VoluntaryDto;
import cl.rollers.tbdproject.SQL.JPA.mappers.VoluntaryMapper;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.features.Feature;
import cl.rollers.tbdproject.SQL.SQL2O.features.FeatureCollection;
import cl.rollers.tbdproject.SQL.SQL2O.models.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sql2o.Connection;

import java.util.*;

@Service
public class VoluntaryService {

    @Autowired
    DatabaseConnection databaseConnection;

    @Autowired
    private VoluntaryMapper voluntaryMapper;

    @Autowired
    private VoluntaryDao voluntaryDao;

    public FeatureCollection createVoluntary(FeatureCollection featureCollection){
        ArrayList<Object> data = new ArrayList<>();
        //System.out.println(feature.getProperties());

        Feature feature = featureCollection.getFeatures().get(0);
        System.out.println("Properties");
        System.out.println(feature.getProperties());
        Map<String, Object> properties = feature.getProperties();
        Set<Map.Entry<String, Object>> entrySet = properties.entrySet();
        for(Map.Entry<String, Object> entry : entrySet){
            data.add(entry.getValue());
        }
        System.out.println("Data");
        System.out.println(data);
        System.out.println(feature.getGeometry());
        Voluntary voluntary = new Voluntary();
        voluntary.setRut(data.get(0).toString());
        voluntary.setGender(data.get(1).toString());
        voluntary.setMail(data.get(2).toString());
        voluntary.setFirstName(data.get(3).toString());
        //voluntary.setId(Integer.parseInt(data.get(4).toString()));
        voluntary.setAge(Integer.parseInt(data.get(4).toString()));
        voluntary.setLastName(data.get(5).toString());
        voluntary.setLocation(feature.getGeometry());
        voluntaryDao.save(voluntary);
        /*List<Voluntary> voluntaries = voluntaryDao.findAll();
        int newId = 1;
        if(voluntaries.size() != 0){
            newId = voluntaries.get(voluntaries.size()-1).getId() + 1;
        }
        int db = newId % databaseConnection.sql2o.length;
        try(Connection conn = databaseConnection.sql2o[db].open()){
            conn.createQuery(
                    "INSERT INTO voluntary(id, age, firstname, gender, lastname, location, mail, rut) VALUES (:id, :age, :firstname, :gender, :lastname, :location, :mail, :rut)")
                    .addParameter("id", newId)
                    .addParameter("age", Integer.parseInt(data.get(4).toString()))
                    .addParameter("firstname", data.get(3).toString())
                    .addParameter("gender", data.get(1).toString())
                    .addParameter("lastname", data.get(5).toString())
                    .addParameter("location", "st_geomfromtext('POINT(-79 -70)')")
                    //.addParameter("location", feature.getGeometry())
                    .addParameter("mail", data.get(2).toString())
                    .addParameter("rut", data.get(0).toString())
                    .executeAndFetch(Emergency.class);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return featureCollection;
    }

    public FeatureCollection getAllVoluntaries(){
        List<Voluntary> voluntaryList = voluntaryDao.findAll();
        System.out.println(voluntaryList.get(0).getId());
        if(voluntaryList.isEmpty()){
            return new FeatureCollection();
        }
        FeatureCollection featureCollection = new FeatureCollection();
        for (Voluntary voluntary : voluntaryList) {
            HashMap<String, Object> properties = new HashMap<>();
            properties.put("age", voluntary.getAge());
            properties.put("name", voluntary.getFirstName());
            properties.put("lastname", voluntary.getLastName());
            properties.put("gender", voluntary.getGender());
            properties.put("id", voluntary.getId());
            properties.put("mail", voluntary.getMail());
            properties.put("rut", voluntary.getRut());
            Feature feature = new Feature(voluntary.getLocation(), properties);
            featureCollection.addFeature(feature);
        }
        return featureCollection;
    }

    public FeatureCollection findVoluntaryById(Integer id){
        if(voluntaryDao.findById(id).isPresent()){
            Voluntary voluntary = voluntaryDao.findById(id).get();
            FeatureCollection featureCollection = new FeatureCollection();

            HashMap<String, Object> properties = new HashMap<>();
            properties.put("age", voluntary.getAge());
            properties.put("name", voluntary.getFirstName());
            properties.put("lastname", voluntary.getLastName());
            properties.put("gender", voluntary.getGender());
            properties.put("id", voluntary.getId());
            properties.put("mail", voluntary.getMail());
            properties.put("rut", voluntary.getRut());
            Feature feature = new Feature(voluntary.getLocation(), properties);
            featureCollection.addFeature(feature);
            return featureCollection;
        }
        else{
            return null;
        }

    }

    public void updateVoluntaryData(VoluntaryDto voluntaryDto, Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryFinded.setFirstName(voluntaryDto.getFirstName());
        voluntaryFinded.setLastName(voluntaryDto.getLastName());
        voluntaryFinded.setRut(voluntaryDto.getLastName());
        voluntaryFinded.setMail(voluntaryDto.getMail());
        voluntaryFinded.setGender(voluntaryDto.getGender());
        voluntaryFinded.setAge(voluntaryDto.getAge());
        voluntaryDao.save(voluntaryFinded);
    }

    public void deleteVoluntary(Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryDao.delete(voluntaryFinded);
    }
 }