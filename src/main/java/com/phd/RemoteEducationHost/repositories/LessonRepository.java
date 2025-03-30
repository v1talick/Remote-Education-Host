package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Lesson;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    Lesson getLessonById(Integer id);
    Lesson getLessonWithDetailsById(Integer id);
    Lesson getLessonByGroupIdAndDay(Integer groupId, DayOfWeek day);
    List<Lesson> getAllLessons();
    List<Lesson> getLessonsByDay(DayOfWeek day);
    List<Lesson> getLessonsByGroupId(Integer groupId);
    List<Lesson> getLessonsByTeacherId(Integer teacherId);
    List<Lesson> getLessonsByClassId(Integer disciplineId);
    void saveLesson(Lesson lesson);
    void updateLesson(Lesson lesson);
    void deleteLesson(Integer lessonId);
}
