package cl.rollers.tbdproject.SQL.dao;

import cl.rollers.tbdproject.SQL.models.VoluntaryTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntaryTaskDao extends JpaRepository<VoluntaryTask, Integer> {
    VoluntaryTask findVoluntaryTaskById(Integer id);
}