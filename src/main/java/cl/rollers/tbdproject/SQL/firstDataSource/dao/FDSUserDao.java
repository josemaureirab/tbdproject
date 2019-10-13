package cl.rollers.tbdproject.SQL.firstDataSource.dao;


import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface FDSUserDao extends JpaRepository<FDSUser, Long> {
    FDSUser findById(long id);
    FDSUser findFDSUserById(long id);
    ArrayList<FDSUser> findAll();
    Optional<FDSUser> findByUsername(String username);
    FDSUser findFDSUserByUsername(String userName);
}
