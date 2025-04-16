package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.TeacherDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Teacher;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
public class TeacherMapper {
    public static Teacher mapToEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        if(teacherDTO == null) {
            return teacher;
        }
        teacher.setId(teacherDTO.getId());
        teacher.setScienceDegree(teacherDTO.getScienceDegree());
        teacher.setDepartment(teacherDTO.getDepartment());
//        teacher.setEmail(teacherDTO.getEmail());
//        teacher.setFirstName(teacherDTO.getFirstName());
//        teacher.setLastName(teacherDTO.getLastName());
//        teacher.setCreateAt(teacherDTO.getCreateAt());
//        teacher.setBirthdayDate(teacherDTO.getBirthdayDate());

        return teacher;
    }

    public static TeacherDTO mapToDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        if(teacher == null) {
            return teacherDTO;
        }
        teacherDTO.setId(teacher.getId());
        teacherDTO.setScienceDegree(teacher.getScienceDegree());
        teacherDTO.setDepartment(teacher.getDepartment());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setCreateAt(teacher.getCreateAt());
        teacherDTO.setBirthdayDate(teacher.getBirthdayDate());
//        teacherDTO.setRoles(teacher.getRoles());

        return teacherDTO;
    }
}
