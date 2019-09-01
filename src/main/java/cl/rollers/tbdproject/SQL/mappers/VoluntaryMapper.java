package cl.rollers.tbdproject.SQL.mappers;

import cl.rollers.tbdproject.SQL.dto.VoluntaryDto;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VoluntaryMapper {
    public Voluntary mapToModel(VoluntaryDto voluntaryDto){
        Voluntary voluntary = new Voluntary();
        voluntary.setId(voluntaryDto.getId());
        voluntary.setName(voluntaryDto.getName());
        voluntary.setLastName(voluntaryDto.getLastName());
        voluntary.setRut(voluntaryDto.getRut());
        voluntary.setAge(voluntaryDto.getAge());
        return voluntary;
    }

    public VoluntaryDto mapToDto(Voluntary voluntary){
        VoluntaryDto voluntaryDto = new VoluntaryDto();
        voluntaryDto.setId(voluntary.getId());
        voluntaryDto.setName(voluntary.getName());
        voluntaryDto.setLastName(voluntary.getLastName());
        voluntaryDto.setRut(voluntary.getRut());
        voluntaryDto.setAge(voluntary.getAge());
        return voluntaryDto;
    }

    public List<VoluntaryDto> mapToDtoArrayList(List<Voluntary> voluntaryList){
        List<VoluntaryDto> voluntaryDtoList = new ArrayList<>();
        for (Voluntary voluntary: voluntaryList){
            VoluntaryDto voluntaryDto = new VoluntaryDto();
            voluntaryDto = this.mapToDto(voluntary);
            voluntaryDtoList.add(voluntaryDto);

        }
        return voluntaryDtoList;

    }

}
