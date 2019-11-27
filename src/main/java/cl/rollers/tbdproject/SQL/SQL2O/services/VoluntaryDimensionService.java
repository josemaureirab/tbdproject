package cl.rollers.tbdproject.SQL.SQL2O.services;

import cl.rollers.tbdproject.SQL.SQL2O.dao.DimensionDao;
import cl.rollers.tbdproject.SQL.SQL2O.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.SQL2O.dao.VoluntaryDimensionDao;
import cl.rollers.tbdproject.SQL.SQL2O.dto.VoluntaryDimensionDto;
import cl.rollers.tbdproject.SQL.SQL2O.mappers.VoluntaryDimensionMapper;
import cl.rollers.tbdproject.SQL.SQL2O.models.Dimension;
import cl.rollers.tbdproject.SQL.SQL2O.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VoluntaryDimensionService {

    @Autowired
    private VoluntaryDimensionDao voluntaryDimensionDao;

    @Autowired
    private VoluntaryDao voluntaryDao;

    @Autowired
    private DimensionDao dimensionDao;

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
        voluntaryDimensionFinded.setVoluntary_id(voluntaryDimensionDto.getVoluntary_id());
        voluntaryDimensionFinded.setDimension_id(voluntaryDimensionDto.getDimension_id());
        voluntaryDimensionDao.save(voluntaryDimensionFinded);
    }

    public void deleteVoluntaryDimension(int id){
        voluntaryDimensionDao.delete(voluntaryDimensionDao.findVoluntaryDimensionById(id));
    }

    public VoluntaryDimensionDto createVoluntaryWithDimension(Integer voluntaryId, Integer dimensionId) {
        Voluntary voluntary = voluntaryDao.findById(voluntaryId).get();
        Dimension dimension = dimensionDao.findById(dimensionId).get();
        VoluntaryDimension voluntaryDimension= new VoluntaryDimension();
        voluntaryDimension.setVoluntary_id(voluntary.getId());
        voluntaryDimension.setDimension_id(dimension.getId());
        return voluntaryDimensionMapper.mapToDto(voluntaryDimensionDao.save(voluntaryDimension));
    }
}
