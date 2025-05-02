package com.phd.RemoteEducationHost.mappers.rowmappers;

import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Teacher;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeacherRowMapper implements RowMapper {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getInt("teacher_id"));
        teacher.setScienceDegree(ScienceDegree.getEnum(rs.getString("science_degree")));
        Department department = new Department();
        department.setId(rs.getInt("department"));
        teacher.setDepartment(department);
        teacher.setBirthdayDate(rs.getDate("birthday_date"));
        teacher.setCreateAt(rs.getDate("creation_date"));
        teacher.setEmail(rs.getString("email"));
        teacher.setFirstName(rs.getString("firstname"));
        teacher.setLastName(rs.getString("lastname"));
        teacher.setPassword(rs.getString("encrypted_password"));
        return teacher;
    }
}
