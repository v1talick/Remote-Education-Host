package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.configuration.SystemTestConfiguration;
import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.enteties.Lesson;
import com.phd.RemoteEducationHost.enteties.enums.LessonType;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(SystemTestConfiguration.class)
public class LessonRepositoryTest {
    @Autowired
    LessonRepository lessonRepository;

    @Test
    public void saveLessonTest() {
        lessonRepository.saveLesson(getLessonTestInstance());
        assertEquals(3, lessonRepository.getAllLessons().size());
    }
    @Test
    public void getLessonTest() {
        Lesson expectedLesson = new Lesson();
        expectedLesson.setId(2);
        expectedLesson.setLessonLink("https://university.edu/dsp-lab");
        Class aClass = new Class();
        aClass.setId(2);
        expectedLesson.setAClass(aClass);
        expectedLesson.setLessonType(LessonType.LABORATORY_LESSON);
        expectedLesson.setLessonNumber(3);
        expectedLesson.setDayOfWeek(DayOfWeek.WEDNESDAY);
//        assertTrue(lessonRepository.getLessonById(2).isPresent());
        assertEquals(expectedLesson, lessonRepository.getLessonById(2));
    }
    @Test
    public void getLessonWithDetails() {
        Lesson lessonFromDB = lessonRepository.getLessonWithDetailsById(1);
//        assertTrue(lessonRepository.getLessonWithDetailsById(1).isPresent());
        assertEquals("https://university.edu/algorithms-class", lessonFromDB.getLessonLink());
        assertTrue(lessonFromDB.getAClass().isActive());
        assertEquals(lessonFromDB.getAClass().getTeacher().getScienceDegree(), ScienceDegree.BACHELOR_OF_SCIENCE);
        assertEquals(lessonFromDB.getAClass().getGroup().getName(), "CS-101");
        assertEquals(lessonFromDB.getAClass().getDiscipline().getName(), "Algorithms and Data Structures");
    }
    @Test
    public void getAllLessons() {
        List<Lesson> lessons = lessonRepository.getAllLessons();
        assertEquals("https://university.edu/algorithms-class", lessons.get(0).getLessonLink());
        assertTrue(lessons.get(0).getAClass().isActive());
        assertEquals(lessons.get(0).getAClass().getTeacher().getScienceDegree(), ScienceDegree.BACHELOR_OF_SCIENCE);
        assertEquals(lessons.get(0).getAClass().getGroup().getName(), "CS-101");
        assertEquals(lessons.get(0).getAClass().getDiscipline().getName(), "Algorithms and Data Structures");
    }
    @Test
    public void getLessonsByDayTest() {
        DayOfWeek day = DayOfWeek.MONDAY;
        List<Lesson> lessonsOnMonday = lessonRepository.getLessonsByDay(day).stream().filter(l -> l.getDayOfWeek().equals(day)).toList();
        assertEquals(lessonRepository.getLessonsByDay(day).size(), lessonsOnMonday.size());
    }
    @Test
    public void getLessonsByClassId() {
        List<Lesson> lessons = lessonRepository.getLessonsByClassId(1).stream().filter(l -> l.getAClass().getId() == 1).toList();
        assertEquals(lessonRepository.getLessonsByClassId(1).size(), lessons.size());
    }
    @Test
    public void getLessonsByTeacherId() {
        List<Lesson> lessons = lessonRepository.getAllLessons().stream().filter(l -> l.getAClass().getTeacher().getId() == 1).toList();
        assertEquals(lessonRepository.getLessonsByTeacherId(1).size(), lessons.size());
    }
    @Test
    public void updateLessonTest() {
        Lesson lesson = lessonRepository.getLessonById(1);
        lesson.setDayOfWeek(DayOfWeek.FRIDAY);
        lessonRepository.updateLesson(lesson);
        assertEquals(DayOfWeek.FRIDAY, lesson.getDayOfWeek());
    }
    @Test
    public void deleteLessonTest() {
        lessonRepository.deleteLesson(3);
//        assertTrue(lessonRepository.getLessonById(3).isEmpty());
        assertEquals(2, lessonRepository.getAllLessons().size());
    }
    public static Lesson getLessonTestInstance() {
        Lesson lesson = new Lesson();
        lesson.setId(228);
        lesson.setLessonNumber(4);
        lesson.setLessonType(LessonType.PRACTICAL_LESSON);
        lesson.setLessonLink("https://www.youtube.com/watch?v=3jpUDB7E39c");
        lesson.setDayOfWeek(DayOfWeek.FRIDAY);
        Class aClass = new Class();
        aClass.setId(1);
        lesson.setAClass(aClass);

        return lesson;
    }
}
