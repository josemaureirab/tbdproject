package cl.rollers.tbdproject.SQL.firstDataSource.dao;


import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSVoluntary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FDSVoluntaryDao extends JpaRepository<FDSVoluntary, Integer> {
  ArrayList<FDSVoluntary> findAll();
  FDSVoluntary findFDSVoluntaryById(Integer id);
}
