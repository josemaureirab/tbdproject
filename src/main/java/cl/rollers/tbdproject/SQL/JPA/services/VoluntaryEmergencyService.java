/*
package cl.rollers.tbdproject.SQL.JPA.services;

import cl.rollers.tbdproject.SQL.JPA.dao.EmergencyDao;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryDao;
import cl.rollers.tbdproject.SQL.JPA.dao.VoluntaryEmergencyDao;
import cl.rollers.tbdproject.SQL.JPA.dto.VoluntaryEmergencyDto;
import cl.rollers.tbdproject.SQL.JPA.mappers.VoluntaryEmergencyMapper;
import cl.rollers.tbdproject.SQL.JPA.models.Emergency;
import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.JPA.models.VoluntaryEmergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoluntaryEmergencyService {

    @Autowired
    private VoluntaryDao voluntaryDao;

    @Autowired
    private EmergencyDao emergencyDao;

    @Autowired
    private VoluntaryEmergencyDao voluntaryEmergencyDao;

    @Autowired
    private VoluntaryEmergencyMapper voluntaryEmergencyMapper;


    public List<VoluntaryEmergencyDto> getAllVoluntaryEmergencies(){
        List<VoluntaryEmergency> voluntaryEmergencyList = voluntaryEmergencyDao.findAll();
        return voluntaryEmergencyMapper.mapToDtoArrayList(voluntaryEmergencyList);
    }

    public VoluntaryEmergencyDto createVoluntaryEmergency(VoluntaryEmergencyDto roleDto){
        return voluntaryEmergencyMapper.mapToDto(voluntaryEmergencyDao.save(voluntaryEmergencyMapper.mapToModel(roleDto)));
    }

    public VoluntaryEmergencyDto findVoluntaryEmergencyById(int id){
        if(voluntaryEmergencyDao.findById(id).isPresent()){
            return voluntaryEmergencyMapper.mapToDto(voluntaryEmergencyDao.findVoluntaryEmergencyById(id));
        }else{
            return null;
        }
    }

    public void updateVoluntaryEmergencyData(VoluntaryEmergencyDto roleDto, int id){
        VoluntaryEmergency voluntaryEmergencyFinded = voluntaryEmergencyDao.findVoluntaryEmergencyById(id);
        voluntaryEmergencyFinded.setVoluntary(roleDto.getVoluntary_id());
        voluntaryEmergencyFinded.setEmergency(roleDto.getEmergency_id());
        voluntaryEmergencyDao.save(voluntaryEmergencyFinded);
    }

    public void deleteVoluntaryEmergency(int id){
        voluntaryEmergencyDao.delete(voluntaryEmergencyDao.findVoluntaryEmergencyById(id));
    }

    public VoluntaryEmergencyDto createVoluntaryWithEmergency(Integer voluntaryId, Integer emergencyId) {
        Voluntary voluntary = voluntaryDao.findById(voluntaryId).get();
        Emergency emergency = emergencyDao.findById(emergencyId).get();
        VoluntaryEmergency voluntaryEmergency= new VoluntaryEmergency();
        voluntaryEmergency.setVoluntary(voluntary.getId());
        voluntaryEmergency.setEmergency(emergency.getId());
        return voluntaryEmergencyMapper.mapToDto(voluntaryEmergencyDao.save(voluntaryEmergency));
    }
}*/
