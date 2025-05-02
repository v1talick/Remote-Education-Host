package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.StudentMapper;
import com.phd.RemoteEducationHost.repositories.StudentRepository;
import com.phd.RemoteEducationHost.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void saveStudent(StudentDTO studentDTO) {
        try {
            studentRepository.saveStudent(StudentMapper.studentDTOToStudent(studentDTO));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Duplicate key");
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("foreign key / null constraint violations");
        } catch (DataAccessException e) {
            throw new InvalidArgumentException("Database access error");
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
