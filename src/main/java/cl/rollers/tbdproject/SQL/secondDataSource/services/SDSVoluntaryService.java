package cl.rollers.tbdproject.SQL.secondDataSource.services;

import cl.rollers.tbdproject.SQL.secondDataSource.dao.SDSVoluntaryDao;
import cl.rollers.tbdproject.SQL.secondDataSource.dto.SDSVoluntaryDto;
import cl.rollers.tbdproject.SQL.secondDataSource.mappers.SDSVoluntaryMapper;
import cl.rollers.tbdproject.SQL.secondDataSource.models.SDSVoluntary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SDSVoluntaryService {
    @Autowired
    private SDSVoluntaryMapper voluntaryMapper;

    @Autowired
    private SDSVoluntaryDao voluntaryDao;

    public SDSVoluntaryDto createVoluntary(SDSVoluntaryDto voluntaryDto){
        return voluntaryMapper.mapToDto(voluntaryDao.save(voluntaryMapper.mapToModel(voluntaryDto)));
    }

    public List<SDSVoluntaryDto> getAllVoluntaries(){
        List<SDSVoluntary> voluntaryList = voluntaryDao.findAll();
        return voluntaryMapper.mapToDtoArrayList(voluntaryList);
    }

    public SDSVoluntaryDto findVoluntaryById(Integer id){
        if(voluntaryDao.findById(id).isPresent()){
            return voluntaryMapper.mapToDto(voluntaryDao.findById(id).get());
        }
        else{
            return null;
        }

    }

    public void updateVoluntaryData(SDSVoluntaryDto voluntaryDto, Integer id){
        SDSVoluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryFinded.setName(voluntaryDto.getName());
        voluntaryFinded.setLastName(voluntaryDto.getLastName());
        voluntaryFinded.setRut(voluntaryDto.getLastName());
        voluntaryFinded.setMail(voluntaryDto.getMail());
        voluntaryFinded.setGender(voluntaryDto.getGender());
        voluntaryFinded.setAge(voluntaryDto.getAge());
        voluntaryDao.save(voluntaryFinded);
    }

    public void deleteVoluntary(Integer id){
        SDSVoluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryDao.delete(voluntaryFinded);
    }
 }