package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Discipline;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class DisciplineRepositoryTest {
    @Autowired
    DisciplineRepository disciplineRepository;

    @Test
    public void saveDisciplineTest() {
        Discipline discipline = new Discipline(0, "testName", "testDescription");
        disciplineRepository.saveDiscipline(discipline);
        assertEquals(3, disciplineRepository.getAllDisciplines().size());
        assertEquals("testName", disciplineRepository.getDisciplineById(4).getName());
    }

    @Test
    public void getDisciplineByIdTest() {
        Discipline discipline = disciplineRepository.getDisciplineById(1);
        assertEquals("Algorithms and Data Structures", discipline.getName());
    }

    @Test
    public void updateDisciplineTest() {
        Discipline discipline = new Discipline(2, "testName2", "testDescription");
        disciplineRepository.updateDiscipline(discipline);
        assertEquals("testName2", disciplineRepository.getDisciplineById(2).getName());
    }

    @Test
    public void getAllDisciplinesTest() {
        List<Discipline> disciplines = disciplineRepository.getAllDisciplines();
        assertNotNull(disciplines.get(1).getDescription());
        assertEquals("Algorithms and Data Structures", disciplines.get(0).getName());
    }

    @Test
    public void deleteDisciplineTest() {
        disciplineRepository.deleteDiscipline(3);
        assertEquals(2, disciplineRepository.getAllDisciplines().size());
        assertThrows(EmptyResultDataAccessException.class, () -> disciplineRepository.getDisciplineById(3));
    }
}
