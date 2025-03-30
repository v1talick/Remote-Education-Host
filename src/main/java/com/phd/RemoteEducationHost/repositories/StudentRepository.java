package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student getStudentById(Integer id);
    List<Student> getAllStudents();
    List<Student> getStudentsByGroupId(Integer groupId);
    void saveStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Integer studentId);
}

