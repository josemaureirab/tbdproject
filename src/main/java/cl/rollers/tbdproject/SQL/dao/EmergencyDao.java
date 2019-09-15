package cl.rollers.tbdproject.SQL.dao;

import cl.rollers.tbdproject.SQL.models.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EmergencyDao extends JpaRepository<Emergency, Integer> {
    ArrayList<Emergency> findAll();
    Emergency findEmergencyById(Integer id);
}