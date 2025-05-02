package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.DepartmentDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import org.mapstruct.Mapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper {

    public static DepartmentDTO departmentToDepartmentDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        if (department == null) {
            return departmentDTO;
        }
        departmentDTO.setId(department.getId());
        if (department.getName() == null) {
            return departmentDTO;
        }
        departmentDTO.setName(department.getName());
        departmentDTO.setDescription(department.getDescription());
        departmentDTO.setCreatedAt(department.getCreatedAt());

        return departmentDTO;
    }

    public static Department departmentDTOtoDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        if (departmentDTO == null) {
            return department;
        }
        department.setId(departmentDTO.getId());
        if (departmentDTO.getName() == null) {
            return department;
        }
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        department.setCreatedAt(departmentDTO.getCreatedAt());

        return department;
    }
}
