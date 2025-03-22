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
    public Teacher getTeacherById(int id) {
        String sql = "select * from teachers t " +
                "join profiles p on p.profile_id=t.teacher_id " +
                "where t.teacher_id=?";

        return (Teacher) jdbcTemplate.queryForObject(sql, teacherMapper, id);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        String sql = "select * from teachers t " +
                "join profiles p on p.profile_id=t.teacher_id";

        return jdbcTemplate.query(sql, teacherMapper);
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        String sql = "insert into teachers (teacher_id, science_degree, department) values (?,?,?)";
        jdbcTemplate.update(sql, teacher.getId(), teacher.getScienceDegree().toString(), teacher.getDepartment().getId());
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        String sql = "update teachers set science_degree=?, department=? where teacher_id=?";
        jdbcTemplate.update(sql, teacher.getScienceDegree().toString()
                , teacher.getDepartment().getId(), teacher.getId());
    }

    @Override
    public void deleteTeacher(int teacherId) {
        String sql = "delete from teachers where teacher_id=?";
        jdbcTemplate.update(sql, teacherId);
    }
}
