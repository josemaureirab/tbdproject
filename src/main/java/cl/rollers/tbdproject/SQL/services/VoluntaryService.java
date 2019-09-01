package cl.rollers.tbdproject.SQL.services;

import cl.rollers.tbdproject.SQL.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.mappers.VoluntaryMapper;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.rollers.tbdproject.SQL.dto.VoluntaryDto;



import java.util.ArrayList;
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
        voluntaryFinded.setAge(voluntaryDto.getAge());
    }


    public void deleteVoluntary(Integer id){
        Voluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryDao.delete(voluntaryFinded);
    }

 }
