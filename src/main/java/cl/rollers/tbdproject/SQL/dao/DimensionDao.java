package cl.rollers.tbdproject.SQL.dao;

import cl.rollers.tbdproject.SQL.models.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimensionDao extends JpaRepository<Dimension, Integer> {
    Dimension findDimensionById(Integer id);
}