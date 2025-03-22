package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Teacher;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class TeacherRepositoryTest {
    @Autowired
    TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Teacher teacher = new Teacher(5, "testEmail@mail.com", "testSurname", "testEmail@mail.com"
                , "testPassword", new Date(), new Date(), List.of(), ScienceDegree.ASSOCIATE_OF_SCIENCE, new Department(1));
        teacherRepository.saveTeacher(teacher);
        assertEquals(3, teacherRepository.getAllTeachers().size());
    }

    @Test
    public void getTeacherByIdTest() {
        Teacher teacher = teacherRepository.getTeacherById(3);
        assertEquals("carol.williams@example.com", teacher.getEmail());
        assertEquals(ScienceDegree.BACHELOR_OF_SCIENCE, teacher.getScienceDegree());
    }

    @Test
    public void updateTeacherTest() {
        Teacher teacher = teacherRepository.getTeacherById(4);
        teacher.setScienceDegree(ScienceDegree.ASSOCIATE_OF_SCIENCE);
        teacherRepository.updateTeacher(teacher);
        assertEquals(ScienceDegree.ASSOCIATE_OF_SCIENCE, teacherRepository.getTeacherById(4).getScienceDegree());
    }
    @Test
    public void deleteTeacherTest() {
        teacherRepository.deleteTeacher(5);
        assertEquals(2, teacherRepository.getAllTeachers().size());
//        assertFalse(teacherRepository.getTeacherById(5).isPresent());
    }
}