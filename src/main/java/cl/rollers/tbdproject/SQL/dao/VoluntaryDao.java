package cl.rollers.tbdproject.SQL.dao;


import cl.rollers.tbdproject.SQL.models.Voluntary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface VoluntaryDao extends JpaRepository<Voluntary, Integer> {
  ArrayList<Voluntary> findAll();
  Voluntary findVoluntaryById(Integer id);
}
