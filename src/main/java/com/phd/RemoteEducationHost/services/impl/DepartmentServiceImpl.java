package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.DepartmentDTO;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.DepartmentMapper;
import com.phd.RemoteEducationHost.repositories.DepartmentRepository;
import com.phd.RemoteEducationHost.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO getDepartmentById(Integer id) {
        return DepartmentMapper.departmentToDepartmentDTO(departmentRepository.getDepartmentById(id));
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.getAllDepartments().stream().map(DepartmentMapper::departmentToDepartmentDTO).toList();
    }

    @Override
    public void saveDepartment(DepartmentDTO department) {
        try {
            departmentRepository.saveDepartment(DepartmentMapper.departmentDTOtoDepartment(department));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Department with such name already exists");
        }
    }

    @Override
    public void updateDepartment(DepartmentDTO department) {
        try {
            departmentRepository.updateDepartment(DepartmentMapper.departmentDTOtoDepartment(department));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Department with such name already exists");
        }
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        try {
            departmentRepository.deleteDepartment(departmentId);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Department with such id does is used in other entities");
        }
    }
}
