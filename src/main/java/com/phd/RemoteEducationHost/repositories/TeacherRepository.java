package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {
    Optional<Teacher> getTeacherById(int id);
    List<Teacher> getAllTeachers();
    void saveTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(int teacherId);
}
