package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public StudentDTO getStudentById(int id);
    public List<StudentDTO> getAllStudents();
    public void saveStudent(StudentCreationDTO studentCreationDTO);
    public void updateStudent(StudentCreationDTO studentCreationDTO);
    public void deleteStudentById(int id);
}
