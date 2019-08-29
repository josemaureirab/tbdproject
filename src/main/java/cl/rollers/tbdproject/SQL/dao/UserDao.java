package cl.rollers.tbdproject.SQL.dao;


import cl.rollers.tbdproject.SQL.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
