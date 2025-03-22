package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
//        assertEquals("testName", departmentRepository.getDepartmentById(1).getName());
        assertEquals(4, departmentRepository.getAllDepartments().size());
    }
    @Test
    void getDepartmentByIdTest() {
        Department department = departmentRepository.getDepartmentById(1);
        assertEquals("Computer Science", department.getName());
    }

    @Test
    void updateDepartmentTest() {
//        jdbcTemplate.update("INSERT INTO departments (department_name, description, created_at) VALUES (?, ?, ?)",
//                "HR", "Handles recruitment", new java.sql.Date(System.currentTimeMillis()));
        Department department = new Department(1, "IT", "Handles technology", new java.sql.Date(System.currentTimeMillis()));
        departmentRepository.updateDepartment(department);
        Department updatedDepartment = departmentRepository.getDepartmentById(1);
        assertEquals("IT", updatedDepartment.getName());
    }

    @Test
    //?cascade delete?
    void deleteDepartmentTest() {
//        jdbcTemplate.update("INSERT INTO departments (department_name, description, created_at) VALUES (?, ?, ?)",
//                "HR", "Handles recruitment", new java.sql.Date(System.currentTimeMillis()));
        departmentRepository.deleteDepartment(4);

        //TODO: make this way in other deleteTests
        assertThrows(EmptyResultDataAccessException.class
                , () -> departmentRepository.getDepartmentById(4));
    }
//    public static Department getTestDepartment() {
//        return new Department(0, "testName", "testDescription", new Date());
//    }
}
