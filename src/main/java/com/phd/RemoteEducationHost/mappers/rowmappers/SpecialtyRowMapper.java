package com.phd.RemoteEducationHost.mappers.rowmappers;

import com.phd.RemoteEducationHost.DTOs.SpecialtyDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Specialty;
import com.phd.RemoteEducationHost.mappers.DepartmentMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SpecialtyRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Specialty specialty = new Specialty();
        specialty.setId(rs.getInt("specialty_id"));
        specialty.setName(rs.getString("specialty_name"));

        Department department = new Department();
        department.setId(rs.getInt("department"));

        if(rs.getMetaData().getColumnCount() > 3) {
            department.setName(rs.getString("department_name"));
            department.setDescription(rs.getString("description"));
            department.setCreatedAt(rs.getDate("created_at"));
        }

        specialty.setDepartment(department);
        return specialty;
    }

}
