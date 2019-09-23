package cl.rollers.tbdproject.SQL.dao;


import cl.rollers.tbdproject.SQL.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    User findById(long id);
    User findUserById(long id);
    ArrayList<User> findAll();
    Optional<User> findByUsername(String username);
    User findUserByUsername(String userName);
}
