package cl.rollers.tbdproject.SQL.JPA.services;

import cl.rollers.tbdproject.DB.SQL2O.DatabaseConnection;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.JPA.dto.VoluntaryDto;
import cl.rollers.tbdproject.SQL.JPA.mappers.VoluntaryMapper;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.features.Feature;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Voluntary voluntary = new Voluntary();
        Map<String, Object> properties = feature.getProperties();
        System.out.println(feature.getProperties().toString());
        Set<Map.Entry<String, Object>> entrySet = properties.entrySet();
        for(Map.Entry<String, Object> entry : entrySet){
            data.add(entry.getValue());
        }
        System.out.println("data");
        System.out.println(data.toString());
        voluntary.setRut(data.get(0).toString());
        voluntary.setFirstName(data.get(1).toString());
        voluntary.setGender(data.get(2).toString());
        voluntary.setMail(data.get(3).toString());
        voluntary.setLatitude(data.get(4).toString());
        Float age = Float.parseFloat(data.get(5).toString());
        voluntary.setAge(age.intValue());
        voluntary.setLastName(data.get(6).toString());
        voluntary.setLongitude(data.get(7).toString());
        voluntary.setLocation((Point) feature.getGeometry());
        //voluntary.setLongitude(feature.getGeometry().);
        //voluntary.setLatitude();
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

    public void updateVoluntaryData(Feature feature, Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        ArrayList<Object> data = new ArrayList<>();
        Map<String, Object> properties = feature.getProperties();
        System.out.println(feature.getProperties().toString());
        Set<Map.Entry<String, Object>> entrySet = properties.entrySet();
        for(Map.Entry<String, Object> entry : entrySet){
            data.add(entry.getValue());
        }
        System.out.println("data");
        System.out.println(data.toString());
        voluntaryFinded.setRut(data.get(0).toString());
        voluntaryFinded.setFirstName(data.get(1).toString());
        voluntaryFinded.setGender(data.get(2).toString());
        voluntaryFinded.setMail(data.get(3).toString());
        voluntaryFinded.setLatitude(data.get(4).toString());
        Float age = Float.parseFloat(data.get(5).toString());
        voluntaryFinded.setAge(age.intValue());
        voluntaryFinded.setLastName(data.get(6).toString());
        voluntaryFinded.setLongitude(data.get(7).toString());
        voluntaryFinded.setLocation((Point) feature.getGeometry());
        voluntaryDao.save(voluntaryFinded);
    }

    public void deleteVoluntary(Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryDao.delete(voluntaryFinded);
    }
 }