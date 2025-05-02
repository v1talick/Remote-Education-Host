package com.phd.RemoteEducationHost.mappers.rowmappers;

import com.phd.RemoteEducationHost.enteties.*;
import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("task_id"));
        task.setDescription(rs.getString("description"));
        task.setDeadline(rs.getDate("deadline"));
        task.setFilePath((String) rs.getObject("file_path"));
        Class aClass = new Class();
        aClass.setId(rs.getInt("class_"));

        if (rs.getMetaData().getColumnCount() > 5) {
            aClass.setStartedAt(rs.getDate("started"));
            aClass.setActive(rs.getBoolean("active"));
            Discipline discipline = new Discipline();
            discipline.setId(rs.getInt("discipline"));
            Group group = new Group();
            group.setId(rs.getInt("group_"));
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("teacher"));
            if (rs.getMetaData().getColumnCount() > 14) {
                discipline.setName(rs.getString("discipline_name"));
                discipline.setDescription(rs.getString("description"));
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
                group.setId(rs.getInt("group_id"));
                group.setName(rs.getString("group_name"));
                group.setCreationDate(rs.getDate("creation_date"));
                Specialty specialty = new Specialty();
                specialty.setId(rs.getInt("specialty"));
                group.setSpecialty(specialty);
            }
            aClass.setDiscipline(discipline);
            aClass.setGroup(group);
            aClass.setTeacher(teacher);
        }
        task.setAClass(aClass);
        return task;
    }
}
