package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {
    Teacher getTeacherById(Integer id);

    List<Teacher> getAllTeachers();

    List<Teacher> getAllTeachersFromDepartment(Integer departmentId);

    void saveTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(Integer teacherId);
}
