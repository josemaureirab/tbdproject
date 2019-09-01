package cl.rollers.tbdproject.SQL.mappers;

import cl.rollers.tbdproject.SQL.dto.EmergencyDto;
import cl.rollers.tbdproject.SQL.models.Emergency;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmergencyMapper {
    public Emergency mapToModel(EmergencyDto emergencyDto){
        Emergency emergency = new Emergency();
        emergency.setId(emergencyDto.getId());
        emergency.setName(emergencyDto.getName());
        emergency.setDescription(emergencyDto.getDescription());
        return emergency;
    }

    public EmergencyDto mapToDto(Emergency emergency){
        EmergencyDto emergencyDto = new EmergencyDto();
        emergencyDto.setId(emergency.getId());
        emergencyDto.setName(emergency.getName());
        return emergencyDto;
    }

    public List<EmergencyDto> mapToDtoArrayList(List<Emergency> emergencyList){
        List<EmergencyDto> emergencyDtoList = new ArrayList<>();
        for (Emergency emergency: emergencyList){
            EmergencyDto emergencyDto = new EmergencyDto();
            emergencyDto = this.mapToDto(emergency);
            emergencyDtoList.add(emergencyDto);

        }
        return emergencyDtoList;
    }

}
