package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Teacher;
import com.phd.RemoteEducationHost.mappers.rowmappers.TeacherRowMapper;
import com.phd.RemoteEducationHost.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepository {
    private final JdbcTemplate jdbcTemplate;

    private final TeacherRowMapper teacherRowMapper;

    @Override
    public Teacher getTeacherById(Integer id) {
        String sql = "select * from teachers t " +
                "join profiles p on p.profile_id=t.teacher_id " +
                "where t.teacher_id=?";

        return (Teacher) jdbcTemplate.queryForObject(sql, teacherRowMapper, id);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        String sql = "select * from teachers t " +
                "join profiles p on p.profile_id=t.teacher_id";

        return jdbcTemplate.query(sql, teacherRowMapper);
    }

    @Override
    public List<Teacher> getAllTeachersFromDepartment(Integer departmentId) {
        String sql = "select * from teachers t " +
                "join profiles p on p.profile_id=t.teacher_id " +
                "where t.department=?";
        return jdbcTemplate.query(sql, teacherRowMapper, departmentId);
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        String sql = "INSERT INTO teachers (teacher_id, science_degree, department) " +
                "VALUES (?, ?::science_degree_enum, ?)";
        jdbcTemplate.update(
                sql,
                teacher.getId(),
                teacher.getScienceDegree().toString(),
                teacher.getDepartment().getId()
        );
    }


    @Override
    public void updateTeacher(Teacher teacher) {
        String sql = "update teachers set science_degree=?, department=? where teacher_id=?";
        jdbcTemplate.update(sql, teacher.getScienceDegree().toString()
                , teacher.getDepartment().getId(), teacher.getId());
    }

    @Override
    public void deleteTeacher(Integer teacherId) {
        String sql = "delete from teachers where teacher_id=?";
        jdbcTemplate.update(sql, teacherId);
    }
}
