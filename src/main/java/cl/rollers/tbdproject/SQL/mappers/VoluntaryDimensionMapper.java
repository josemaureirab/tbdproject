package cl.rollers.tbdproject.SQL.mappers;

import cl.rollers.tbdproject.SQL.dto.VoluntaryDimensionDto;
import cl.rollers.tbdproject.SQL.models.VoluntaryDimension;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VoluntaryDimensionMapper {
    public VoluntaryDimension mapToModel(VoluntaryDimensionDto voluntaryDimensionDto){
        VoluntaryDimension voluntaryDimension = new VoluntaryDimension();
        voluntaryDimension.setId(voluntaryDimensionDto.getId());
        voluntaryDimension.setVoluntary_id(voluntaryDimensionDto.getVoluntary_id());
        voluntaryDimension.setDimension_id(voluntaryDimensionDto.getDimension_id());
        return voluntaryDimension;
    }

    public List<VoluntaryDimensionDto> mapToDtoArrayList(List<VoluntaryDimension> roleList){
        int i;
        ArrayList<VoluntaryDimensionDto> roleDtoArrayList = new ArrayList<>();
        for(i=0;i<roleList.size();i++){
            roleDtoArrayList.add(mapToDto(roleList.get(i)));
        }
        return roleDtoArrayList;
    }

    public VoluntaryDimensionDto mapToDto (VoluntaryDimension voluntaryDimension){
        VoluntaryDimensionDto voluntaryDimensionDto = new VoluntaryDimensionDto();
        voluntaryDimensionDto.setId(voluntaryDimension.getId());
        voluntaryDimensionDto.setVoluntary_id(voluntaryDimension.getVoluntary_id());
        voluntaryDimensionDto.setDimension_id(voluntaryDimension.getDimension_id());
        return voluntaryDimensionDto;
    }
}