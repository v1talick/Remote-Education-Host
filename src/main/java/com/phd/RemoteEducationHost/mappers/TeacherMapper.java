package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.TeacherDTO;
import com.phd.RemoteEducationHost.enteties.Teacher;
public class TeacherMapper {
    public static Teacher mapToEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        if(teacherDTO == null) {
            return teacher;
        }
        teacher.setId(teacherDTO.getId());
        teacher.setScienceDegree(teacherDTO.getScienceDegree());
        teacher.setDepartment(DepartmentMapper.departmentDTOtoDepartment(teacherDTO.getDepartmentDTO()));
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
        teacherDTO.setDepartmentDTO(DepartmentMapper.departmentToDepartmentDTO(teacher.getDepartment()));
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setCreateAt(teacher.getCreateAt());
        teacherDTO.setBirthdayDate(teacher.getBirthdayDate());
//        teacherDTO.setRoles(teacher.getRoles());

        return teacherDTO;
    }
}
