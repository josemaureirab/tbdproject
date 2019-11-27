package cl.rollers.tbdproject.SQL.SQL2O.dao;


import cl.rollers.tbdproject.SQL.SQL2O.models.Voluntary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface VoluntaryDao extends JpaRepository<Voluntary, Integer> {
  ArrayList<Voluntary> findAll();
  Voluntary findVoluntaryById(Integer id);
}
