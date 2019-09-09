package cl.rollers.tbdproject.SQL.dao;

import cl.rollers.tbdproject.SQL.models.VoluntaryEmergency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntaryEmergencyDao extends JpaRepository<VoluntaryEmergency, Integer> {
    VoluntaryEmergency findVoluntaryEmergencyById(Integer id);
}