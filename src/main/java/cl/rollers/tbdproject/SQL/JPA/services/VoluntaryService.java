/*
package cl.rollers.tbdproject.SQL.JPA.services;

import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.JPA.dto.VoluntaryDto;
import cl.rollers.tbdproject.SQL.JPA.mappers.VoluntaryMapper;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoluntaryService {
    @Autowired
    private VoluntaryMapper voluntaryMapper;

    @Autowired
    private VoluntaryDao voluntaryDao;

    public VoluntaryDto createVoluntary(VoluntaryDto voluntaryDto){
        return voluntaryMapper.mapToDto(voluntaryDao.save(voluntaryMapper.mapToModel(voluntaryDto)));
    }

    public List<VoluntaryDto> getAllVoluntaries(){
        List<Voluntary> voluntaryList = voluntaryDao.findAll();
        return voluntaryMapper.mapToDtoArrayList(voluntaryList);
    }

    public VoluntaryDto findVoluntaryById(Integer id){
        if(voluntaryDao.findById(id).isPresent()){
            return voluntaryMapper.mapToDto(voluntaryDao.findById(id).get());
        }
        else{
            return null;
        }

    }

    public void updateVoluntaryData(VoluntaryDto voluntaryDto, Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryFinded.setName(voluntaryDto.getName());
        voluntaryFinded.setLastName(voluntaryDto.getLastName());
        voluntaryFinded.setRut(voluntaryDto.getLastName());
        voluntaryFinded.setMail(voluntaryDto.getMail());
        voluntaryFinded.setGender(voluntaryDto.getGender());
        voluntaryFinded.setAge(voluntaryDto.getAge());
        voluntaryFinded.setLatitude(voluntaryDto.getLatitude());
        voluntaryFinded.setLongitude(voluntaryDto.getLongitude());
        voluntaryDao.save(voluntaryFinded);
    }

    public void deleteVoluntary(Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryDao.delete(voluntaryFinded);
    }
 }
*/