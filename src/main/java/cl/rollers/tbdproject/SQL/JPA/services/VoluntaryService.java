package cl.rollers.tbdproject.SQL.JPA.services;

import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.JPA.dto.VoluntaryDto;
import cl.rollers.tbdproject.SQL.JPA.mappers.VoluntaryMapper;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.features.Feature;
import cl.rollers.tbdproject.SQL.SQL2O.features.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class VoluntaryService {
    @Autowired
    private VoluntaryMapper voluntaryMapper;

    @Autowired
    private VoluntaryDao voluntaryDao;

    public FeatureCollection createVoluntary(FeatureCollection featureCollection){
        List<Feature> feature = featureCollection.getFeatures();
        Map<String, Object> propierties = feature.get(0).getPropierties();
        Set<Map.Entry<String, Object>> entrySet = propierties.entrySet();
        for(Map.Entry<String, Object> entry : entrySet){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
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
            HashMap<String, Object> propierties = new HashMap<>();
            propierties.put("age", voluntary.getAge());
            propierties.put("name", voluntary.getFirstName());
            propierties.put("lastname", voluntary.getLastName());
            propierties.put("gender", voluntary.getGender());
            propierties.put("id", voluntary.getId());
            propierties.put("mail", voluntary.getMail());
            propierties.put("rut", voluntary.getRut());
            Feature feature = new Feature(voluntary.getLocation(), propierties);
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