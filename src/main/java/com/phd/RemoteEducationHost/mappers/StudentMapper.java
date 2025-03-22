package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("student_id"));
        student.setEmail(rs.getString("email"));
        student.setPassword(rs.getString("encrypted_password"));
        student.setFirstName(rs.getString("firstname"));
        student.setLastName(rs.getString("lastname"));
        student.setBirthdayDate(rs.getDate("birthday_date"));
        student.setCreateAt(rs.getDate("creation_date"));

        Group group = new Group();
        group.setId(rs.getInt("group_"));
        student.setGroup(group);

        return student;
    }

    public static StudentDTO studentToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
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


    public static Student studentCreationToStudent(StudentCreationDTO studentCreationDTO) {
        Student student = new Student();
        student.setId(studentCreationDTO.getId());
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
