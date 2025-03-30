package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {
    Department getDepartmentById(Integer id);
    List<Department> getAllDepartments();
    void saveDepartment(Department department);
    void updateDepartment(Department department);
    void deleteDepartment(Integer departmentId);
}
