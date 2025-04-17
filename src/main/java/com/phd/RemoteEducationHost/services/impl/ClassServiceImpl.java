package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.ClassDTO;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.enteties.enums.Role;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.ClassMapper;
import com.phd.RemoteEducationHost.repositories.ClassRepository;
import com.phd.RemoteEducationHost.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;


    @Override
    public ClassDTO getClassById(Integer id) {
        return ClassMapper.mapToDTO(classRepository.getClassById(id));
    }

    @Override
    public ClassDTO getClassWithDetailsById(Integer id) {
        return ClassMapper.mapToDTO(classRepository.getClassWithDetailsById(id));
    }

    @Override
    public List<ClassDTO> getAllClasses() {
        return classRepository.getAllClasses().stream().map(ClassMapper::mapToDTO).toList();
    }

    @Override
    public List<ClassDTO> getClassesByTeacherId(Integer teacherId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!Objects.equals(user.getId(), teacherId) && !user.getRoles().contains(Role.ADMIN)) {
            throw new AccessDeniedException("You can`t see this teacher`s classes");
        }
        return classRepository.getClassesByTeacherId(teacherId).stream().map(ClassMapper::mapToDTO).toList();
    }

    @Override
    public List<ClassDTO> getClassesByGroupId(Integer groupId) {
        return classRepository.getClassesByGroupId(groupId).stream().map(ClassMapper::mapToDTO).toList();
    }

    @Override
    public void saveClass(ClassDTO classDTO) {
        try {
            classRepository.saveClass(ClassMapper.mapToEntity(classDTO));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Invalid class data " + e.getMessage());
        }
    }

    @Override
    public void updateClass(ClassDTO classDTO) {
        try {
            classRepository.updateClass(ClassMapper.mapToEntity(classDTO));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Invalid class data " + e.getMessage());
        }
    }

    @Override
    public void deleteClass(Integer classId) {
        try {
            classRepository.deleteClass(classId);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Class is used in another entity");
        }
    }
}
