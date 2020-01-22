package cl.rollers.tbdproject.SQL.JPA.services;

import cl.rollers.tbdproject.DB.SQL2O.DatabaseConnection;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.JPA.dto.VoluntaryDto;
import cl.rollers.tbdproject.SQL.JPA.mappers.VoluntaryMapper;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.features.Feature;
//import cl.rollers.tbdproject.SQL.SQL2O.features.FeatureCollection;
import com.vividsolutions.jts.geom.Point;
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

    public Feature createVoluntary(Feature feature){
        ArrayList<Object> data = new ArrayList<>();
        System.out.println(feature.getType());
        Voluntary voluntary = new Voluntary();
        Map<String, Object> properties = feature.getProperties();
        Set<Map.Entry<String, Object>> entrySet = properties.entrySet();
        for(Map.Entry<String, Object> entry : entrySet){
            data.add(entry.getValue());
        }
        voluntary.setRut(data.get(0).toString());
        voluntary.setGender(data.get(1).toString());
        voluntary.setMail(data.get(2).toString());
        voluntary.setFirstName(data.get(3).toString());
        voluntary.setAge(Integer.parseInt(data.get(4).toString()));
        voluntary.setLastName(data.get(5).toString());
        voluntary.setLocation((Point) feature.getGeometry());
        voluntaryDao.save(voluntary);
        return feature;
    }

    public ArrayList<Feature> getAllVoluntaries(){
        List<Voluntary> voluntaryList = voluntaryDao.findAll();
        ArrayList<Feature> featureCollection = new ArrayList<>();
        if(voluntaryList.isEmpty()){
            return featureCollection;
        }

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
            featureCollection.add(feature);
        }
        return featureCollection;
    }

    public Feature findVoluntaryById(Integer id){
        if(voluntaryDao.findById(id).isPresent()){
            Voluntary voluntary = voluntaryDao.findById(id).get();
            HashMap<String, Object> properties = new HashMap<>();
            properties.put("age", voluntary.getAge());
            properties.put("name", voluntary.getFirstName());
            properties.put("lastname", voluntary.getLastName());
            properties.put("gender", voluntary.getGender());
            properties.put("id", voluntary.getId());
            properties.put("mail", voluntary.getMail());
            properties.put("rut", voluntary.getRut());
            Feature feature = new Feature(voluntary.getLocation(), properties);
            return feature;
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
        voluntaryFinded.setLocation(voluntaryDto.getLocation());
        voluntaryDao.save(voluntaryFinded);
    }

    public void deleteVoluntary(Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryDao.delete(voluntaryFinded);
    }
 }