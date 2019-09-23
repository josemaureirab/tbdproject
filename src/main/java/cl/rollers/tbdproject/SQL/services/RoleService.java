package cl.rollers.tbdproject.SQL.services;

import cl.rollers.tbdproject.SQL.dao.RoleDao;
import cl.rollers.tbdproject.SQL.dto.RoleDto;
import cl.rollers.tbdproject.SQL.mappers.RoleMapper;
import cl.rollers.tbdproject.SQL.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMapper roleMapper;


    public List<RoleDto> getAllRoles(){
        List<Role> roleList = roleDao.findAll();
        return roleMapper.mapToDtoArrayList(roleList);
    }

    public RoleDto createRole(RoleDto roleDto){
        return roleMapper.mapToDto(roleDao.save(roleMapper.mapToModel(roleDto)));
    }

    public RoleDto findRoleById(int id){
        if(roleDao.findById(id).isPresent()){
            return roleMapper.mapToDto(roleDao.findRoleById(id));
        }else{
            return null;
        }
    }

    public void updateRoleData(RoleDto roleDto, int id){
        Role roleFinded = roleDao.findRoleById(id);
        roleFinded.setName(roleDto.getName());
        roleDao.save(roleFinded);
    }

    public void deleteRole(int id){
        roleDao.delete(roleDao.findRoleById(id));
    }
}