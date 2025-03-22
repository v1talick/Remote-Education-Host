package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@TestPropertySource(locations = "classpath:test-application.properties")
@SpringJUnitConfig(SystemTestConfiguration.class)
public class DepartmentRepositoryTest {
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void saveDepartmentTest(){
        Department department = new Department(0, "testName", "testDescription", new Date());
        departmentRepository.saveDepartment(department);
//        assertEquals("testName", departmentRepository.getDepartmentById(1).get().getName());
        assertEquals(4, departmentRepository.getAllDepartments().size());
    }
    @Test
    void getDepartmentByIdTest() {
        Optional<Department> departmentOptional = departmentRepository.getDepartmentById(1);
        assertTrue(departmentOptional.isPresent());
        assertEquals("Computer Science", departmentOptional.get().getName());
    }

    @Test
    void updateDepartmentTest() {
//        jdbcTemplate.update("INSERT INTO departments (department_name, description, created_at) VALUES (?, ?, ?)",
//                "HR", "Handles recruitment", new java.sql.Date(System.currentTimeMillis()));
        Department department = new Department(1, "IT", "Handles technology", new java.sql.Date(System.currentTimeMillis()));
        departmentRepository.updateDepartment(department);
        Optional<Department> updatedDepartment = departmentRepository.getDepartmentById(1);
        assertTrue(updatedDepartment.isPresent());
        assertEquals("IT", updatedDepartment.get().getName());
    }

    @Test
    //?cascade delete?
    void deleteDepartmentTest() {
//        jdbcTemplate.update("INSERT INTO departments (department_name, description, created_at) VALUES (?, ?, ?)",
//                "HR", "Handles recruitment", new java.sql.Date(System.currentTimeMillis()));
        departmentRepository.deleteDepartment(4);
        Optional<Department> department = departmentRepository.getDepartmentById(4);
        assertFalse(department.isPresent());
    }
//    public static Department getTestDepartment() {
//        return new Department(0, "testName", "testDescription", new Date());
//    }
}
