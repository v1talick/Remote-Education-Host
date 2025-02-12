package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Teacher;
import com.phd.RemoteEducationHost.mappers.TeacherMapper;
import com.phd.RemoteEducationHost.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class TeacherRepositoryImpl implements TeacherRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public Optional<Teacher> getTeacherById(int id) {
        String sql = "select * from teachers t" +
                "join profiles p on p.profile_id=t.teacher_id" +
                "where t.teacher_id=?";
        try {
            return Optional.of((Teacher) jdbcTemplate.query(sql, teacherMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        String sql = "select * from teachers t " +
                "join profiles p on p.profile_id=t.teacher_id";

        return jdbcTemplate.query(sql, teacherMapper);
    }

    @Override
    public void saveTeacher(Teacher teacher) {

    }

    @Override
    public void updateTeacher(Teacher teacher) {

    }

    @Override
    public void deleteTeacher(int teacherId) {

    }
}
