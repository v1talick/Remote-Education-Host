package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Specialty;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GroupMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//        System.out.println(rs.getMetaData().getColumnCount());
        Group group = new Group();
        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("group_name"));
        group.setCreationDate(rs.getDate("creation_date"));

        Specialty specialty = new Specialty();
        specialty.setId(rs.getInt("specialty"));

        group.setSpecialty(specialty);
        if(rs.getMetaData().getColumnCount() > 4) {
            specialty.setName(rs.getString("specialty_name"));
            specialty.setDepartment(new Department(rs.getInt("department_id")));
        }
        return group;
    }
    public static Group groupDTOtoGroup(GroupDTO groupDTO){
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());
        group.setSpecialty(groupDTO.getSpecialty());
        group.setCreationDate(groupDTO.getCreationDate());

        return group;
    }

    public static GroupDTO groupToGroupDTO(Group groupDTO){
        GroupDTO group = new GroupDTO();
        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());
        group.setSpecialty(groupDTO.getSpecialty());
        group.setCreationDate(groupDTO.getCreationDate());

        return group;
    }
}
