package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.enteties.Discipline;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class ClassRepositoryTest {
    @Autowired
    ClassRepository classRepository;

    @Test
    public void saveClassTest() {
        Class newClass = new Class();
        newClass.setId(228);
        Teacher teacher = new Teacher();
        teacher.setId(4);
        Discipline discipline = new Discipline();
        discipline.setId(3);
        Group group = new Group();
        group.setId(2);
        newClass.setTeacher(teacher);
        newClass.setGroup(group);
        newClass.setDiscipline(discipline);
        newClass.setActive(true);
        Date date = new Date(Timestamp.valueOf("2024-03-21 00:00:00").getTime());
        newClass.setStartedAt(date);

        classRepository.saveClass(newClass);
        assertEquals(3, classRepository.getAllClasses().size());
    }

    @Test
    public void getClassByIdTest() {
        Class expectedClass = new Class();
        expectedClass.setId(1);
        Teacher teacher = new Teacher();
        teacher.setId(3);
        Discipline discipline = new Discipline();
        discipline.setId(1);
        Group group = new Group();
        group.setId(1);
        expectedClass.setTeacher(teacher);
        expectedClass.setGroup(group);
        expectedClass.setDiscipline(discipline);
        expectedClass.setActive(true);
        Date date = new Date(Timestamp.valueOf("2024-02-01 00:00:00").getTime());
        expectedClass.setStartedAt(date);
        Class classFromDB = classRepository.getClassById(1);
        assertEquals(expectedClass, classFromDB);
    }

    @Test
    public void updateClass() {
        Class aClass = classRepository.getClassById(2);
        aClass.setStartedAt(new Date(Timestamp.valueOf("2025-02-27 00:00:00").getTime()));
        classRepository.updateClass(aClass);
        assertEquals("2025-02-27", classRepository.getClassById(2).getStartedAt().toString());
    }

    @Test
    public void deleteClass() {
        classRepository.deleteClass(3);
        assertEquals(2, classRepository.getAllClasses().size());
    }
}
