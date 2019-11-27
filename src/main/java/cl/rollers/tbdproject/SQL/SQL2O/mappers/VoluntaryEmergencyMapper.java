package cl.rollers.tbdproject.SQL.SQL2O.mappers;

import cl.rollers.tbdproject.SQL.SQL2O.dto.VoluntaryEmergencyDto;
import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryEmergency;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class VoluntaryEmergencyMapper {
    public VoluntaryEmergency mapToModel(VoluntaryEmergencyDto voluntaryEmergencyDto){
        VoluntaryEmergency voluntaryEmergency = new VoluntaryEmergency();
        voluntaryEmergency.setId(voluntaryEmergencyDto.getId());
        voluntaryEmergency.setVoluntary(voluntaryEmergencyDto.getVoluntary_id());
        voluntaryEmergency.setEmergency(voluntaryEmergencyDto.getEmergency_id());
        return voluntaryEmergency;
    }

    public VoluntaryEmergencyDto mapToDto(VoluntaryEmergency voluntaryEmergency){
        VoluntaryEmergencyDto voluntaryEmergencyDto = new VoluntaryEmergencyDto();
        voluntaryEmergencyDto.setId(voluntaryEmergency.getId());
        voluntaryEmergencyDto.setVoluntary_id(voluntaryEmergency.getVoluntary());
        voluntaryEmergencyDto.setEmergency_id(voluntaryEmergency.getEmergency());
        return voluntaryEmergencyDto;
    }

    public List<VoluntaryEmergencyDto> mapToDtoArrayList(List<VoluntaryEmergency> voluntaryEmergencyList) {
        int i;
        ArrayList<VoluntaryEmergencyDto> voluntaryEmergencyDtoArrayList = new ArrayList<>();
        for (i = 0; i < voluntaryEmergencyList.size(); i++) {
            voluntaryEmergencyDtoArrayList.add(mapToDto(voluntaryEmergencyList.get(i)));
        }
        return voluntaryEmergencyDtoArrayList;
    }
}