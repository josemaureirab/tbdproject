package cl.rollers.tbdproject.SQL.dao;

import cl.rollers.tbdproject.SQL.models.Emergency;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import cl.rollers.tbdproject.SQL.models.VoluntaryEmergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoluntaryEmergencyDao extends JpaRepository<VoluntaryEmergency, Integer> {
    VoluntaryEmergency findVoluntaryEmergencyById(Integer id);
    VoluntaryEmergency findVoluntaryEmergencyByEmergencyAndVoluntary(Integer emergencyId, Integer voluntaryId);
}