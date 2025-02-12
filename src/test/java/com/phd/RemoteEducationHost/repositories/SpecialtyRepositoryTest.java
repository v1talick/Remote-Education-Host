package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Specialty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class SpecialtyRepositoryTest {
    @Autowired
    SpecialtyRepository specialtyRepository;

    @Test
    public void saveSpecialtyTest() {
        Specialty specialty = new Specialty(0, "testName", new Department(1));
        specialtyRepository.saveSpecialty(specialty);
        assertEquals(7, specialtyRepository.getAllSpecialties().size());
    }
    @Test
    public void getSpecialtyByIdTest() {
        Specialty specialty = specialtyRepository.getSpecialtyById(1).get();
        assertEquals("Software Engineering", specialty.getName());
    }
    @Test
    public void updateSpecialtyTest() {
        Specialty specialty = new Specialty(1, "testName", new Department(1));
        specialtyRepository.updateSpecialty(specialty);
        assertEquals("testName", specialtyRepository.getSpecialtyById(1).get().getName());
    }
    @Test
    public void deleteSpecialtyTest() {
        specialtyRepository.deleteSpecialty(7);
        assertFalse(specialtyRepository.getSpecialtyById(7).isPresent());
    }
}
