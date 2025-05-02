package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;

import java.util.List;

public interface StudentService {
    StudentDTO getStudentById(Integer id);

    List<StudentDTO> getAllStudents();

    List<StudentDTO> getAllStudentsByGroupId(Integer groupId);

    void saveStudent(StudentDTO studentDTO);

    void updateStudent(StudentCreationDTO studentCreationDTO);

    void deleteStudentById(Integer id);
}
