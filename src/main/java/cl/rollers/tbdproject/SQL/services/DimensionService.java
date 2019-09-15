package cl.rollers.tbdproject.SQL.services;

import cl.rollers.tbdproject.SQL.dao.DimensionDao;
import cl.rollers.tbdproject.SQL.dto.DimensionDto;
import cl.rollers.tbdproject.SQL.mappers.DimensionMapper;
import cl.rollers.tbdproject.SQL.models.Dimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public DimensionDto findDimensionById(int id){
        if(dimensionDao.findById(id).isPresent()){
            return dimensionMapper.mapToDto(dimensionDao.findDimensionById(id));
        }else{
            return null;
        }
    }

    public void updateDimensionData(DimensionDto dimensionDto, int id){
        Dimension dimensionFinded = dimensionDao.findDimensionById(id);
        dimensionFinded.setName(dimensionDto.getName());
        dimensionFinded.setScore(dimensionDto.getScore());
        dimensionFinded.setVoluntaryDimensionList(dimensionDto.getVoluntaryDimensionList());
        dimensionDao.save(dimensionFinded);
    }

    public void deleteDimension(int id){
        dimensionDao.delete(dimensionDao.findDimensionById(id));
    }
}