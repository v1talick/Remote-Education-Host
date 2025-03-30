package com.phd.RemoteEducationHost.mappers.rowmappers;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Student;
import com.phd.RemoteEducationHost.mappers.GroupMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentRowMapper implements RowMapper {
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
}
