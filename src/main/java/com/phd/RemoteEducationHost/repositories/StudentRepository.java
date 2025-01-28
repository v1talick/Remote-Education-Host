package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Optional<Student> getStudentById(int id);
    List<Student> getAllStudents();
    List<Student> getStudentsByGroupId(int groupId);
    void saveStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int studentId);
}

