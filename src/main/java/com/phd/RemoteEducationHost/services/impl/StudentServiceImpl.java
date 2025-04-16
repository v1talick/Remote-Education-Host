package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;
import com.phd.RemoteEducationHost.mappers.StudentMapper;
import com.phd.RemoteEducationHost.repositories.StudentRepository;
import com.phd.RemoteEducationHost.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    public final StudentRepository studentRepository;
    @Override
    public StudentDTO getStudentById(Integer id) {
        return StudentMapper.studentToStudentDTO(studentRepository.getStudentById(id));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.getAllStudents().stream().map(StudentMapper::studentToStudentDTO).toList();
    }

    @Override
    public List<StudentDTO> getAllStudentsByGroupId(Integer groupId) {
        return studentRepository.getStudentsByGroupId(groupId).stream().map(StudentMapper::studentToStudentDTO).toList();
    }

    @Override
    public void saveStudent(StudentCreationDTO studentCreationDTO) {
        //TODO process this exceptions by custom unchecked exception
        try {
            studentRepository.saveStudent(StudentMapper.studentCreationToStudent(studentCreationDTO));
        } catch (DuplicateKeyException e) {
            // handle duplicate key (e.g. log or notify user)
            throw new IllegalArgumentException("Duplicate key");
        } catch (DataIntegrityViolationException e) {
            // handle foreign key / null constraint violations
        } catch (DataAccessException e) {
            // generic catch-all for database issues
        }
    }

    @Override
    public void updateStudent(StudentCreationDTO studentCreationDTO) {
        studentRepository.updateStudent(StudentMapper.studentCreationToStudent(studentCreationDTO));
    }

    @Override
    public void deleteStudentById(Integer id) {
        studentRepository.deleteStudent(id);
    }
}
