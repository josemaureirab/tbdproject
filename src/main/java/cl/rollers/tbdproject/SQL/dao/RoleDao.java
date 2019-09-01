package cl.rollers.tbdproject.SQL.dao;

import cl.rollers.tbdproject.SQL.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findRoleById(Integer id);
}