package cl.rollers.tbdproject.SQL.secondDataSource.mappers;

import cl.rollers.tbdproject.SQL.secondDataSource.dto.SDSVoluntaryDto;
import cl.rollers.tbdproject.SQL.secondDataSource.models.SDSVoluntary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Component
public class SDSVoluntaryMapper {
    public SDSVoluntary mapToModel(SDSVoluntaryDto voluntaryDto){
        SDSVoluntary voluntary = new SDSVoluntary();
        voluntary.setId(voluntaryDto.getId());
        voluntary.setName(voluntaryDto.getName());
        voluntary.setLastName(voluntaryDto.getLastName());
        voluntary.setMail(voluntaryDto.getMail());
        voluntary.setGender(voluntaryDto.getGender());
        voluntary.setRut(voluntaryDto.getRut());
        voluntary.setAge(voluntaryDto.getAge());
        return voluntary;
    }

    public SDSVoluntaryDto mapToDto(SDSVoluntary voluntary){
        SDSVoluntaryDto voluntaryDto = new SDSVoluntaryDto();
        voluntaryDto.setId(voluntary.getId());
        voluntaryDto.setName(voluntary.getName());
        voluntaryDto.setLastName(voluntary.getLastName());
        voluntaryDto.setMail(voluntary.getMail());
        voluntaryDto.setGender(voluntary.getGender());
        voluntaryDto.setRut(voluntary.getRut());
        voluntaryDto.setAge(voluntary.getAge());
        return voluntaryDto;
    }

    public List<SDSVoluntaryDto> mapToDtoArrayList(List<SDSVoluntary> voluntaryList){
        List<SDSVoluntaryDto> voluntaryDtoList = new ArrayList<>();
        for (SDSVoluntary voluntary: voluntaryList){
            SDSVoluntaryDto voluntaryDto = new SDSVoluntaryDto();
            voluntaryDto = this.mapToDto(voluntary);
            voluntaryDtoList.add(voluntaryDto);

        }
        return voluntaryDtoList;

    }

}
