package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Student;
import com.phd.RemoteEducationHost.mappers.rowmappers.StudentRowMapper;
import com.phd.RemoteEducationHost.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements com.phd.RemoteEducationHost.repositories.StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final StudentRowMapper studentRowMapper;
    @Override
    public Student getStudentById(Integer id) {
//        String sql = "select * from students s " +
//                "join profiles p on p.profile_id=s.student_id " +
//                "where s.student_id = ?";
        String sql = "SELECT * FROM students s \n" +
                "JOIN profiles p ON p.profile_id = s.student_id \n" +
                "WHERE s.student_id = ?\n";

        return (Student) jdbcTemplate.queryForObject(sql, studentRowMapper, id);
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "select * from students s " +
                "join profiles p on p.profile_id=s.student_id";
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    @Override
    public List<Student> getStudentsByGroupId(Integer groupId) {
        String sql = "select * from students s " +
                "join profiles p on p.profile_id=s.student_id " +
                "where s.group_=?";
        return jdbcTemplate.query(sql, studentRowMapper, groupId);
    }

    @Override
    public void saveStudent(Student student) {
        String sql = "insert into students(student_id, group_) values(?, ?)";

        jdbcTemplate.update(sql, student.getId(), student.getGroup().getId());
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students\n" +
                "\tSET group_=?\n" +
                "\tWHERE student_id=?";
        jdbcTemplate.update(sql,student.getGroup().getId(), student.getId());
        userRepository.updateUser(student);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        String sql = "delete from students where student_id=?";
        jdbcTemplate.update(sql, studentId);
    }
}
