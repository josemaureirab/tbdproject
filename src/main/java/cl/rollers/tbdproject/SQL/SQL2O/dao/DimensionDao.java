package cl.rollers.tbdproject.SQL.SQL2O.dao;

import cl.rollers.tbdproject.SQL.SQL2O.models.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimensionDao extends JpaRepository<Dimension, Integer> {
    Dimension findDimensionById(Integer id);
}