package cl.rollers.tbdproject.SQL.firstDataSource.services;

import cl.rollers.tbdproject.SQL.firstDataSource.dao.FDSVoluntaryDao;
import cl.rollers.tbdproject.SQL.firstDataSource.dto.FDSVoluntaryDto;
import cl.rollers.tbdproject.SQL.firstDataSource.mappers.FDSVoluntaryMapper;
import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSVoluntary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FDSVoluntaryService {
    @Autowired
    private FDSVoluntaryMapper voluntaryMapper;

    @Autowired
    private FDSVoluntaryDao voluntaryDao;

    public FDSVoluntaryDto createFDSVoluntary(FDSVoluntaryDto voluntaryDto){
        return voluntaryMapper.mapToDto(voluntaryDao.save(voluntaryMapper.mapToModel(voluntaryDto)));
    }

    public List<FDSVoluntaryDto> getAllFDSVoluntaries(){
        List<FDSVoluntary> voluntaryList = voluntaryDao.findAll();
        return voluntaryMapper.mapToDtoArrayList(voluntaryList);
    }

    public FDSVoluntaryDto findFDSVoluntaryById(Integer id){
        if(voluntaryDao.findById(id).isPresent()){
            return voluntaryMapper.mapToDto(voluntaryDao.findById(id).get());
        }
        else{
            return null;
        }

    }

    public void updateFDSVoluntaryData(FDSVoluntaryDto voluntaryDto, Integer id){
        FDSVoluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryFinded.setName(voluntaryDto.getName());
        voluntaryFinded.setLastName(voluntaryDto.getLastName());
        voluntaryFinded.setRut(voluntaryDto.getLastName());
        voluntaryFinded.setMail(voluntaryDto.getMail());
        voluntaryFinded.setGender(voluntaryDto.getGender());
        voluntaryFinded.setAge(voluntaryDto.getAge());
        voluntaryDao.save(voluntaryFinded);
    }

    public void deleteFDSVoluntary(Integer id){
        FDSVoluntary voluntaryFinded = voluntaryDao.findById(id).get();
        voluntaryDao.delete(voluntaryFinded);
    }
 }