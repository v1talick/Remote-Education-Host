package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Test
    public void saveStudentTest() {
        Group group = new Group();
        group.setId(1);
        Student student = new Student(5, "testEmail@mail.com", "testSurname"
                , "testEmail@mail.com", "testPassword", new Date(), new Date(), List.of(), group);
        studentRepository.saveStudent(student);
        assertEquals(3, studentRepository.getAllStudents().size());
    }

    @Test
    public void getStudentByIdTest() {
        Group group = new Group();
        group.setId(1);
        Student expectedStudent = new Student(1, "alice.smith@example.com", "hashed_password_1"
                , "Alice", "Smith", new Date(), new Date(), List.of(), group);
        Student student = studentRepository.getStudentById(1);
        assertEquals("hashed_password_1", student.getPassword());
        assertEquals(student, expectedStudent);
    }

    @Test
    public void updateStudentTest() {
        Student student = studentRepository.getStudentById(2);
        Group group = new Group();
        group.setId(6);
        student.setGroup(group);
        studentRepository.updateStudent(student);
        assertEquals(6, studentRepository.getStudentById(2).getGroup().getId());
    }

    @Test
    public void deleteStudentTest() {
        studentRepository.deleteStudent(5);
        assertEquals(2, studentRepository.getAllStudents().size());
//        assertFalse(studentRepository.getStudentById(5).isPresent());
    }
}
