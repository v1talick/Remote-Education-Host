package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.DepartmentDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.mappers.DepartmentMapper;
import com.phd.RemoteEducationHost.mappers.UserMapper;
import com.phd.RemoteEducationHost.repositories.DepartmentRepository;
import com.phd.RemoteEducationHost.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public Optional<DepartmentDTO> getDepartmentById(int id) {
        return departmentRepository.getDepartmentById(id).map(DepartmentMapper::departmentToDepartmentDTO);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.getAllDepartments().stream().map(DepartmentMapper::departmentToDepartmentDTO).toList();
    }

    @Override
    public void saveDepartment(DepartmentDTO department) {
        departmentRepository.saveDepartment(DepartmentMapper.departmentDTOtoDepartment(department));
    }

    @Override
    public void updateDepartment(DepartmentDTO department) {
        departmentRepository.updateDepartment(DepartmentMapper.departmentDTOtoDepartment(department));
    }

    @Override
    public void deleteDepartment(int departmentId) {
        departmentRepository.getDepartmentById(departmentId);
    }
}
