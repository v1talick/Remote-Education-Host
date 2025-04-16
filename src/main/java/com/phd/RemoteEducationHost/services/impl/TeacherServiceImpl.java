package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.TeacherDTO;
import com.phd.RemoteEducationHost.mappers.TeacherMapper;
import com.phd.RemoteEducationHost.repositories.TeacherRepository;
import com.phd.RemoteEducationHost.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        teacherRepository.saveTeacher(TeacherMapper.mapToEntity(teacherDTO));
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
