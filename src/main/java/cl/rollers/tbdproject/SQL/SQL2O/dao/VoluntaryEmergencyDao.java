package cl.rollers.tbdproject.SQL.SQL2O.dao;

//import cl.rollers.tbdproject.SQL.SQL2O.models.Emergency;
//import cl.rollers.tbdproject.SQL.JPA.models.Voluntary;
import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryEmergency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntaryEmergencyDao extends JpaRepository<VoluntaryEmergency, Integer> {
    VoluntaryEmergency findVoluntaryEmergencyById(Integer id);
    VoluntaryEmergency findVoluntaryEmergencyByEmergencyAndVoluntary(Integer emergencyId, Integer voluntaryId);
}