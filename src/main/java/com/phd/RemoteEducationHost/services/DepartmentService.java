package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.DepartmentDTO;
import com.phd.RemoteEducationHost.enteties.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    DepartmentDTO getDepartmentById(Integer id);
    List<DepartmentDTO> getAllDepartments();
    void saveDepartment(DepartmentDTO department);
    void updateDepartment(DepartmentDTO department);
    void deleteDepartment(Integer departmentId);
}
