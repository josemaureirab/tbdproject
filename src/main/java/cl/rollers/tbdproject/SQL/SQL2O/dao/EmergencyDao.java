package cl.rollers.tbdproject.SQL.SQL2O.dao;


import cl.rollers.tbdproject.SQL.SQL2O.models.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EmergencyDao extends JpaRepository<Emergency, Integer> {
    ArrayList<Emergency> findAll();
    Emergency findEmergencyById(Integer id);
}