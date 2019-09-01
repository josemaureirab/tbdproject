package cl.rollers.tbdproject.SQL.dao;


import cl.rollers.tbdproject.SQL.models.Voluntary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntaryDao extends JpaRepository<Voluntary, Integer> {
}
