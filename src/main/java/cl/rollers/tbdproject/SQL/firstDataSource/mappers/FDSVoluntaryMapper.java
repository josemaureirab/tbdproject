package cl.rollers.tbdproject.SQL.firstDataSource.mappers;

import cl.rollers.tbdproject.SQL.firstDataSource.dto.FDSVoluntaryDto;
import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSVoluntary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@Component
public class FDSVoluntaryMapper {
    public FDSVoluntary mapToModel(FDSVoluntaryDto voluntaryDto){
        FDSVoluntary voluntary = new FDSVoluntary();
        voluntary.setId(voluntaryDto.getId());
        voluntary.setName(voluntaryDto.getName());
        voluntary.setLastName(voluntaryDto.getLastName());
        voluntary.setMail(voluntaryDto.getMail());
        voluntary.setGender(voluntaryDto.getGender());
        voluntary.setRut(voluntaryDto.getRut());
        voluntary.setAge(voluntaryDto.getAge());
        return voluntary;
    }

    public FDSVoluntaryDto mapToDto(FDSVoluntary voluntary){
        FDSVoluntaryDto voluntaryDto = new FDSVoluntaryDto();
        voluntaryDto.setId(voluntary.getId());
        voluntaryDto.setName(voluntary.getName());
        voluntaryDto.setLastName(voluntary.getLastName());
        voluntaryDto.setMail(voluntary.getMail());
        voluntaryDto.setGender(voluntary.getGender());
        voluntaryDto.setRut(voluntary.getRut());
        voluntaryDto.setAge(voluntary.getAge());
        return voluntaryDto;
    }

    public List<FDSVoluntaryDto> mapToDtoArrayList(List<FDSVoluntary> voluntaryList){
        List<FDSVoluntaryDto> voluntaryDtoList = new ArrayList<>();
        for (FDSVoluntary voluntary: voluntaryList){
            FDSVoluntaryDto voluntaryDto = new FDSVoluntaryDto();
            voluntaryDto = this.mapToDto(voluntary);
            voluntaryDtoList.add(voluntaryDto);

        }
        return voluntaryDtoList;

    }

}
