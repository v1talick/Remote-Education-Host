package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.SpecialtyDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Specialty;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialtyMapper {

    public static Specialty specialtyDTOtoSpecialty(SpecialtyDTO specialtyDTO) {
        Specialty specialty = new Specialty();
        specialty.setId(specialtyDTO.getId());
        specialty.setName(specialtyDTO.getName());
        specialty.setDepartment(DepartmentMapper.departmentDTOtoDepartment(specialtyDTO.getDepartment()));

        return specialty;
    }

    public static SpecialtyDTO  specialtyToSpecialtyDTO(Specialty specialty) {
        SpecialtyDTO specialtyDTO = new SpecialtyDTO();
        specialtyDTO.setId(specialty.getId());
        specialtyDTO.setName(specialty.getName());
        specialtyDTO.setDepartment(DepartmentMapper.departmentToDepartmentDTO(specialty.getDepartment()));

        return specialtyDTO;
    }
}
