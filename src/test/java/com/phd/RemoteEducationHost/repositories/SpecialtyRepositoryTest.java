package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Specialty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class SpecialtyRepositoryTest {
    @Autowired
    SpecialtyRepository specialtyRepository;

    @Test
    public void saveSpecialtyTest() {
        Department department = new Department();
        department.setId(1);
        Specialty specialty = new Specialty(0, "unique testName", department);
        specialtyRepository.saveSpecialty(specialty);
        assertEquals(7, specialtyRepository.getAllSpecialties().size());
    }

    @Test
    public void getSpecialtyByIdTest() {
        Specialty specialty = specialtyRepository.getSpecialtyById(1);
        assertEquals("Software Engineering", specialty.getName());
    }

    @Test
    public void updateSpecialtyTest() {
        Department department = new Department();
        department.setId(1);
        Specialty specialty = new Specialty(1, "testName", department);
        specialtyRepository.updateSpecialty(specialty);
        assertEquals("testName", specialtyRepository.getSpecialtyById(1).getName());
    }

    @Test
    public void deleteSpecialtyTest() {
        specialtyRepository.deleteSpecialty(7);
//        assertFalse(specialtyRepository.getSpecialtyById(7).isPresent());
    }
}
