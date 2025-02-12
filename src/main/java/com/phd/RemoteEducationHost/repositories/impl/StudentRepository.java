package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Student;
import com.phd.RemoteEducationHost.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class StudentRepository implements com.phd.RemoteEducationHost.repositories.StudentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<Student> getStudentById(int id) {
        String sql = "select * from students s " +
                "join profiles p on p.profile_id=s.student_id" +
                "where s.student_id = ?";
        try {
            return Optional.of((Student) jdbcTemplate.query(sql, (rs, rowNum) -> new Student(
                            rs.getInt("s.student_id"),
                            rs.getString("s.email"),
                            rs.getString("s.encrypted_password"),
                            rs.getString("s.firstname"),
                            rs.getString("s.lastname"),
                            rs.getDate("s.creation_date"),
                            rs.getDate("s.birthday_date"),
                            new Group(rs.getInt("s.group_")))
                    , id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "select * from students s " +
                "join profiles p on p.profile_id=s.student_id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Student(
                rs.getInt("s.student_id"),
                rs.getString("s.email"),
                rs.getString("s.encrypted_password"),
                rs.getString("s.firstname"),
                rs.getString("s.lastname"),
                rs.getDate("s.creation_date"),
                rs.getDate("s.birthday_date"),
                new Group(rs.getInt("s.group_"))));
    }

    @Override
    public List<Student> getStudentsByGroupId(int groupId) {
        String sql = "select * from students s " +
                "join profiles p on p.profile_id=s.student_id" +
                "where s.group_=?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Student(
                rs.getInt("s.student_id"),
                rs.getString("s.email"),
                rs.getString("s.encrypted_password"),
                rs.getString("s.firstname"),
                rs.getString("s.lastname"),
                rs.getDate("s.creation_date"),
                rs.getDate("s.birthday_date"),
                new Group(rs.getInt("s.group_"))), groupId);
    }

    @Override
    public void saveStudent(Student student) {
        String sql = "insert into students(student_id, group_) values(?, ?)";
        jdbcTemplate.update(sql, student.getId(), student.getGroup().getId());
        if(userRepository.getUserById(student.getId()).isEmpty()) {
            userRepository.saveUser(student);
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students\n" +
                "\tSET group_=?\n" +
                "\tWHERE student_id=?";
        jdbcTemplate.update(sql, student.getId());
        userRepository.updateUser(student);
    }

    @Override
    public void deleteStudent(int studentId) {
        String sql = "delete from students where student_id=?";
        jdbcTemplate.update(sql, studentId);
    }
}
