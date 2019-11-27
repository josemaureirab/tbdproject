package cl.rollers.tbdproject.SQL.SQL2O.dao;

import cl.rollers.tbdproject.SQL.SQL2O.models.VoluntaryDimension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntaryDimensionDao extends JpaRepository<VoluntaryDimension, Integer> {
    VoluntaryDimension findVoluntaryDimensionById(Integer id);
}