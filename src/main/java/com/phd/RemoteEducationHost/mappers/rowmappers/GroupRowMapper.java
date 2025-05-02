package com.phd.RemoteEducationHost.mappers.rowmappers;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Specialty;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GroupRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("group_name"));
        group.setCreationDate(rs.getDate("creation_date"));

        Specialty specialty = new Specialty();
        specialty.setId(rs.getInt("specialty"));

        group.setSpecialty(specialty);
        if (rs.getMetaData().getColumnCount() > 4) {
            Department department = new Department();
            department.setId(rs.getInt("department_id"));
            specialty.setDepartment(department);
            specialty.setName(rs.getString("specialty_name"));
        }
        return group;
    }
}
