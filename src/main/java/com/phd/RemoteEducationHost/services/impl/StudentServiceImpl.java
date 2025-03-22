package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;
import com.phd.RemoteEducationHost.mappers.StudentMapper;
import com.phd.RemoteEducationHost.repositories.StudentRepository;
import com.phd.RemoteEducationHost.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    public final StudentRepository studentRepository;
    @Override
    public Optional<StudentDTO> getStudentById(int id) {
        return studentRepository.getStudentById(id).map(StudentMapper::studentToStudentDTO);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.getAllStudents().stream().map(StudentMapper::studentToStudentDTO).toList();
    }

    @Override
    public void saveStudent(StudentCreationDTO studentCreationDTO) {
        studentRepository.saveStudent(StudentMapper.studentCreationToStudent(studentCreationDTO));
    }

    @Override
    public void updateStudent(StudentCreationDTO studentCreationDTO) {
        studentRepository.updateStudent(StudentMapper.studentCreationToStudent(studentCreationDTO));
    }

    @Override
    public void deleteStudentById(int id) {
        studentRepository.deleteStudent(id);
    }
}
