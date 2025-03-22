package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Lesson;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface LessonRepository {
    Lesson getLessonById(int id);
    Lesson getLessonWithDetailsById(int id);
    Lesson getLessonByGroupIdAndDay(int groupId, DayOfWeek day);
    List<Lesson> getAllLessons();
    List<Lesson> getLessonsByDay(DayOfWeek day);
    List<Lesson> getLessonsByGroupId(int groupId);
    List<Lesson> getLessonsByTeacherId(int teacherId);
    List<Lesson> getLessonsByClassId(int disciplineId);
    void saveLesson(Lesson lesson);
    void updateLesson(Lesson lesson);
    void deleteLesson(int lessonId);
}
