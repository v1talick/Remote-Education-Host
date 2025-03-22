package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.enteties.*;
import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.enteties.enums.LessonType;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

@Component
public class LessonMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setId(rs.getInt("lesson_id"));
        lesson.setLessonType(LessonType.getEnum(rs.getString("lesson_type")));
        lesson.setLessonLink(rs.getString("lesson_link"));
        lesson.setLessonNumber(rs.getInt("lesson_number"));
        lesson.setDayOfWeek(DayOfWeek.of(rs.getInt("week_day")));
        Class aClass = new Class();
        aClass.setId(rs.getInt("class_"));

        if(rs.getMetaData().getColumnCount() > 6) {
            aClass.setStartedAt(rs.getDate("started"));
            aClass.setActive(rs.getBoolean("active"));
            Discipline discipline = new Discipline();
            discipline.setId(rs.getInt("discipline"));
            Group group = new Group();
            group.setId(rs.getInt("group_"));
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("teacher"));

                discipline.setName(rs.getString("discipline_name"));
                discipline.setDescription(rs.getString("description"));
//            teacher = (Teacher) new TeacherMapper().mapRow(rs, rowNum);
                teacher.setScienceDegree(ScienceDegree.getEnum(rs.getString("science_degree")));
                teacher.setDepartment(new Department(rs.getInt("department")));
                teacher.setBirthdayDate(rs.getDate("birthday_date"));
                teacher.setCreateAt(rs.getDate("creation_date"));
                teacher.setEmail(rs.getString("email"));
                teacher.setFirstName(rs.getString("firstname"));
                teacher.setLastName(rs.getString("lastname"));
                teacher.setPassword(rs.getString("encrypted_password"));
//            group = (Group) new GroupMapper().mapRow(rs, rowNum);
                group.setId(rs.getInt("group_id"));
                group.setName(rs.getString("group_name"));
                group.setCreationDate(rs.getDate("creation_date"));
                Specialty specialty = new Specialty();
                specialty.setId(rs.getInt("specialty"));
                group.setSpecialty(specialty);

            aClass.setDiscipline(discipline);
            aClass.setGroup(group);
            aClass.setTeacher(teacher);
        }
        lesson.setAClass(aClass);
        return lesson;
    }
}
