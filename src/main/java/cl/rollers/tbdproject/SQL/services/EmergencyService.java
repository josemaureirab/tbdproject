package cl.rollers.tbdproject.SQL.services;

import cl.rollers.tbdproject.SQL.dao.EmergencyDao;
import cl.rollers.tbdproject.SQL.dto.EmergencyDto;
import cl.rollers.tbdproject.SQL.mappers.EmergencyMapper;
import cl.rollers.tbdproject.SQL.models.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmergencyService {

    @Autowired
    private EmergencyDao emergencyDao;

    @Autowired
    private EmergencyMapper emergencyMapper;


    public List<EmergencyDto> getAllEmergencies(){
        ArrayList<Emergency> emergencies = emergencyDao.findAll();
        return emergencyMapper.mapToDtoArrayList(emergencies);
    }

    public EmergencyDto createEmergency(EmergencyDto emergencyDto){
        return emergencyMapper.mapToDto(emergencyDao.save(emergencyMapper.mapToModel(emergencyDto)));
    }

    public EmergencyDto findEmergencyById(int id){
        if(emergencyDao.findById(id).isPresent()){
            return emergencyMapper.mapToDto(emergencyDao.findEmergencyById(id));
        }
        else{
            return null;
        }
    }

    public void updateEmergency(EmergencyDto emergencyDto, int id){
        Emergency emergencyFinded = emergencyDao.findEmergencyById(id);
        emergencyFinded.setName(emergencyDto.getName());
        emergencyFinded.setDescription(emergencyDto.getDescription());
        emergencyDao.save(emergencyFinded);
    }

    public void deleteEmergency(int id){
        Emergency emergencyFinded = emergencyDao.findEmergencyById(id);
        emergencyDao.delete(emergencyFinded);
    }
}
