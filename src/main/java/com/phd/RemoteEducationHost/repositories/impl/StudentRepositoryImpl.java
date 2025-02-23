package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Student;
import com.phd.RemoteEducationHost.mappers.StudentMapper;
import com.phd.RemoteEducationHost.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class StudentRepositoryImpl implements com.phd.RemoteEducationHost.repositories.StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    private final StudentMapper studentMapper;
    @Override
    public Optional<Student> getStudentById(int id) {
//        String sql = "select * from students s " +
//                "join profiles p on p.profile_id=s.student_id " +
//                "where s.student_id = ?";
        String sql = "SELECT * FROM students s \n" +
                "JOIN profiles p ON p.profile_id = s.student_id \n" +
                "WHERE s.student_id = ?\n";
        try {
            return Optional.of((Student) jdbcTemplate.queryForObject(sql, studentMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "select * from students s " +
                "join profiles p on p.profile_id=s.student_id";
        return jdbcTemplate.query(sql, studentMapper);
    }

    @Override
    public List<Student> getStudentsByGroupId(int groupId) {
        String sql = "select * from students s " +
                "join profiles p on p.profile_id=s.student_id " +
                "where s.group_=?";
        return jdbcTemplate.query(sql, studentMapper, groupId);
    }

    @Override
    public void saveStudent(Student student) {
        if(userRepository.getUserById(student.getId()).isEmpty()) {
            userRepository.saveUser(student);
        }
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
    public void deleteStudent(int studentId) {
        String sql = "delete from students where student_id=?";
        jdbcTemplate.update(sql, studentId);
    }
}
