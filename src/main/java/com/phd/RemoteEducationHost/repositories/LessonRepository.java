package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Lesson;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    Optional<Lesson> getLessonById(int id);
    Optional<Lesson> getLessonWithDetailsById(int id);
    List<Lesson> getAllLessons();
    List<Lesson> getLessonsByDay(DayOfWeek day);
    List<Lesson> getLessonsByGroupId(int groupId);
    List<Lesson> getLessonsByTeacherId(int teacherId);
    List<Lesson> getLessonsByClassId(int disciplineId);
    Optional<Lesson> getLessonByGroupIdAndDay(int groupId, DayOfWeek day);
    void saveLesson(Lesson lesson);
    void updateLesson(Lesson lesson);
    void deleteLesson(int lessonId);
}
