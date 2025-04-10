package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.DepartmentDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.mappers.DepartmentMapper;
import com.phd.RemoteEducationHost.mappers.UserMapper;
import com.phd.RemoteEducationHost.repositories.DepartmentRepository;
import com.phd.RemoteEducationHost.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            departmentRepository.saveDepartment(DepartmentMapper.departmentDTOtoDepartment(department));
        }
        throw new RuntimeException("You don`t have permission to add department");
    }

    @Override
    public void updateDepartment(DepartmentDTO department) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            departmentRepository.updateDepartment(DepartmentMapper.departmentDTOtoDepartment(department));
        }
        throw new RuntimeException("You don`t have permission to update department");
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        departmentRepository.deleteDepartment(departmentId);
    }
}
