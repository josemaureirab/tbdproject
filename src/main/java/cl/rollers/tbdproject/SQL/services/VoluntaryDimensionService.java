package cl.rollers.tbdproject.SQL.services;

import cl.rollers.tbdproject.SQL.dao.VoluntaryDimensionDao;
import cl.rollers.tbdproject.SQL.dto.VoluntaryDimensionDto;
import cl.rollers.tbdproject.SQL.mappers.VoluntaryDimensionMapper;
import cl.rollers.tbdproject.SQL.models.VoluntaryDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VoluntaryDimensionService {
    @Autowired
    private VoluntaryDimensionDao voluntaryDimensionDao;

    @Autowired
    private VoluntaryDimensionMapper voluntaryDimensionMapper;


    public List<VoluntaryDimensionDto> getAllVoluntaryDimensions(){
        List<VoluntaryDimension> voluntaryDimensionList = voluntaryDimensionDao.findAll();
        return voluntaryDimensionMapper.mapToDtoArrayList(voluntaryDimensionList);
    }

    public VoluntaryDimensionDto createVoluntaryDimension(VoluntaryDimensionDto voluntaryDimensionDto){
        return voluntaryDimensionMapper.mapToDto(voluntaryDimensionDao.save(voluntaryDimensionMapper.mapToModel(voluntaryDimensionDto)));
    }

    public VoluntaryDimensionDto findVoluntaryDimensionById(int id){
        if(voluntaryDimensionDao.findById(id).isPresent()){
            return voluntaryDimensionMapper.mapToDto(voluntaryDimensionDao.findVoluntaryDimensionById(id));
        }else{
            return null;
        }
    }

    public void updateVoluntaryDimensionData(VoluntaryDimensionDto voluntaryDimensionDto, int id){
        VoluntaryDimension voluntaryDimensionFinded = voluntaryDimensionDao.findVoluntaryDimensionById(id);
        voluntaryDimensionFinded.setVoluntary(voluntaryDimensionDto.getVoluntary());
        voluntaryDimensionFinded.setDimension(voluntaryDimensionDto.getDimension());
        voluntaryDimensionDao.save(voluntaryDimensionFinded);
    }

    public void deleteVoluntaryDimension(int id){
        voluntaryDimensionDao.delete(voluntaryDimensionDao.findVoluntaryDimensionById(id));
    }
}
