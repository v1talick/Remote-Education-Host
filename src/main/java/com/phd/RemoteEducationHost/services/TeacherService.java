package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.TeacherDTO;
import com.phd.RemoteEducationHost.enteties.Teacher;

import java.util.List;

public interface TeacherService {
    TeacherDTO getTeacherById(Integer id);

    List<TeacherDTO> getAllTeachers();

    List<TeacherDTO> getAllTeachersFromDepartment(Integer departmentId);

    void saveTeacher(TeacherDTO teacherDTO);

    void updateTeacher(TeacherDTO teacherDTO);

    void deleteTeacher(Integer teacherId);
}
