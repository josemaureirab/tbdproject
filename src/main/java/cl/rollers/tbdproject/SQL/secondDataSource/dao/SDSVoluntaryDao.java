package cl.rollers.tbdproject.SQL.secondDataSource.dao;


import cl.rollers.tbdproject.SQL.secondDataSource.models.SDSVoluntary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface SDSVoluntaryDao extends JpaRepository<SDSVoluntary, Integer> {
  ArrayList<SDSVoluntary> findAll();
  SDSVoluntary findSDSVoluntaryById(Integer id);
}
