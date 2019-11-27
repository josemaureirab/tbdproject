/*
package cl.rollers.tbdproject.SQL.JPA.mappers;

import cl.rollers.tbdproject.SQL.JPA.dto.DimensionDto;
import cl.rollers.tbdproject.SQL.JPA.models.Dimension;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DimensionMapper {
    public Dimension mapToModel(DimensionDto dimensionDto){
        Dimension dimension = new Dimension();
        dimension.setId(dimensionDto.getId());
        dimension.setName(dimensionDto.getName());
        dimension.setScore(dimensionDto.getScore());
        return dimension;
    }

    public List<DimensionDto> mapToDtoArrayList(List<Dimension> dimensionList){
        int i;
        ArrayList<DimensionDto> dimensionDtoArrayList = new ArrayList<>();
        for(i=0;i<dimensionList.size();i++){
            dimensionDtoArrayList.add(mapToDto(dimensionList.get(i)));
        }
        return dimensionDtoArrayList;
    }

    public DimensionDto mapToDto (Dimension dimension){
        DimensionDto dimensionDto = new DimensionDto();
        dimensionDto.setId(dimension.getId());
        dimensionDto.setName(dimension.getName());
        dimensionDto.setScore(dimension.getScore());
        return dimensionDto;
    }
}
*/
