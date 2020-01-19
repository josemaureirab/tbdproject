package cl.rollers.tbdproject.SQL.SQL2O.dao;


import cl.rollers.tbdproject.SQL.SQL2O.models.Voluntary;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
@Transactional
public interface VoluntaryDao extends JpaRepository<Voluntary, Integer> {
  Voluntary findVoluntaryById(Integer id);
}
