package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Specialty;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GroupMapper {
    public static Group groupDTOtoGroup(GroupDTO groupDTO){
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());
        group.setSpecialty(SpecialtyMapper.specialtyDTOtoSpecialty(groupDTO.getSpecialtyDTO()));
        group.setCreationDate(groupDTO.getCreationDate());

        return group;
    }

    public static GroupDTO groupToGroupDTO(Group groupDTO){
        GroupDTO group = new GroupDTO();
        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());
        group.setSpecialtyDTO(SpecialtyMapper.specialtyToSpecialtyDTO(groupDTO.getSpecialty()));
        group.setCreationDate(groupDTO.getCreationDate());

        return group;
    }
}
