package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;
import com.phd.RemoteEducationHost.enteties.Student;

public class StudentMapper {
    public static StudentDTO studentToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        if (student == null) {
            return studentDTO;
        }
        studentDTO.setId(student.getId());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setCreateAt(student.getCreateAt());
        studentDTO.setBirthdayDate(student.getBirthdayDate());
        studentDTO.setRoles(studentDTO.getRoles());
        studentDTO.setGroupDTO(GroupMapper.groupToGroupDTO(student.getGroup()));

        return studentDTO;
    }

    public static Student studentDTOToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        if (studentDTO == null) {
            return student;
        }
        student.setId(studentDTO.getId());
        student.setEmail(studentDTO.getEmail());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setCreateAt(studentDTO.getCreateAt());
        student.setBirthdayDate(studentDTO.getBirthdayDate());
        student.setRoles(student.getRoles());
        student.setGroup(GroupMapper.groupDTOtoGroup(studentDTO.getGroupDTO()));

        return student;
    }

    public static Student studentCreationToStudent(StudentCreationDTO studentCreationDTO) {
        Student student = new Student();
        if (studentCreationDTO == null) {
            return student;
        }
        student.setEmail(studentCreationDTO.getEmail());
        student.setPassword(studentCreationDTO.getPassword());
        student.setFirstName(studentCreationDTO.getFirstName());
        student.setLastName(studentCreationDTO.getLastName());
        student.setCreateAt(studentCreationDTO.getCreateAt());
        student.setBirthdayDate(studentCreationDTO.getBirthdayDate());
        student.setRoles(student.getRoles());
        student.setGroup(GroupMapper.groupDTOtoGroup(studentCreationDTO.getGroupDTO()));

        return student;
    }
}
