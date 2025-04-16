package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public StudentDTO getStudentById(Integer id);
    public List<StudentDTO> getAllStudents();
    List<StudentDTO> getAllStudentsByGroupId(Integer groupId);
    public void saveStudent(StudentCreationDTO studentCreationDTO);
    public void updateStudent(StudentCreationDTO studentCreationDTO);
    public void deleteStudentById(Integer id);
}
