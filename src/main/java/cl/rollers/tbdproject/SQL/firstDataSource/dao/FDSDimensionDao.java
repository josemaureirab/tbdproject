package cl.rollers.tbdproject.SQL.firstDataSource.dao;

import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSDimension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FDSDimensionDao extends JpaRepository<FDSDimension, Integer> {
  FDSDimension findFDSDimensionById(Integer id);
}
