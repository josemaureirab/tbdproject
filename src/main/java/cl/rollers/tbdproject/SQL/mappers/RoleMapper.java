package cl.rollers.tbdproject.SQL.mappers;

import cl.rollers.tbdproject.SQL.dto.RoleDto;
import cl.rollers.tbdproject.SQL.models.Role;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper {
    public Role mapToModel(RoleDto roleDto){
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        role.setUserList(roleDto.getUserList());
        return role;
    }

    public List<RoleDto> mapToDtoArrayList(List<Role> roleList){
        int i;
        ArrayList<RoleDto> roleDtoArrayList = new ArrayList<>();
        for(i=0;i<roleList.size();i++){
            roleDtoArrayList.add(mapToDto(roleList.get(i)));
        }
        return roleDtoArrayList;
    }

    public RoleDto mapToDto (Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setUserList(role.getUserList());
        return roleDto;
    }
}