package cl.rollers.tbdproject.SQL.dao;

import cl.rollers.tbdproject.SQL.models.VoluntaryDimension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntaryDimensionDao extends JpaRepository<VoluntaryDimension, Integer> {
    VoluntaryDimension findVoluntaryDimensionById(Integer id);
}