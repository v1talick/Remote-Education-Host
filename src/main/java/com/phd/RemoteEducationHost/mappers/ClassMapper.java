package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.enteties.Discipline;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Class aClass = new Class();
        aClass.setId(rs.getInt("class_id"));
        aClass.setStartedAt(rs.getDate("started"));
        aClass.setActive(rs.getBoolean("active"));
        Discipline discipline = new Discipline();
        discipline.setId(rs.getInt("discipline"));
        Group group = new Group();
        group.setId(rs.getInt("group_"));
        Teacher teacher = new Teacher();
        teacher.setId(rs.getInt("teacher"));
        if(rowNum > 6) {

        }
        aClass.setDiscipline(discipline);
        aClass.setGroup(group);
        aClass.setTeacher(teacher);
        return aClass;
    }
}
