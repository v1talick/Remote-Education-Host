package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.TeacherDTO;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.TeacherMapper;
import com.phd.RemoteEducationHost.repositories.TeacherRepository;
import com.phd.RemoteEducationHost.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public TeacherDTO getTeacherById(Integer id) {
        return TeacherMapper.mapToDTO(teacherRepository.getTeacherById(id));
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.getAllTeachers().stream().map(TeacherMapper::mapToDTO).toList();
    }

    @Override
    public List<TeacherDTO> getAllTeachersFromDepartment(Integer departmentId) {
        return teacherRepository.getAllTeachersFromDepartment(departmentId).stream().map(TeacherMapper::mapToDTO).toList();
    }

    @Override
    public void saveTeacher(TeacherDTO teacherDTO) {
        try {
            teacherRepository.saveTeacher(TeacherMapper.mapToEntity(teacherDTO));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Teacher with such profile %d id already exists".formatted(teacherDTO.getId()));
        }
    }

    @Override
    public void updateTeacher(TeacherDTO teacherDTO) {
        teacherRepository.updateTeacher(TeacherMapper.mapToEntity(teacherDTO));
    }

    @Override
    public void deleteTeacher(Integer teacherId) {
        teacherRepository.deleteTeacher(teacherId);
    }
}
