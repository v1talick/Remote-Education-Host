package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.DepartmentDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("department_id"));
        department.setName(rs.getString("department_name"));
        return null;
    }
    public static DepartmentDTO departmentToDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        if(department.getName() == null) {
            return departmentDTO;
        }
        departmentDTO.setName(department.getName());
        departmentDTO.setDescription(department.getDescription());
        departmentDTO.setCreatedAt(department.getCreatedAt());

        return departmentDTO;
    }
    public static Department departmentDTOtoDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        if(departmentDTO.getName() == null) {
            return department;
        }
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        department.setCreatedAt(departmentDTO.getCreatedAt());

        return department;
    }
}
