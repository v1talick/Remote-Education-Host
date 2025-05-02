package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.LessonDTO;

import java.time.DayOfWeek;
import java.util.List;

public interface LessonService {
    LessonDTO getLessonById(Integer id);

    LessonDTO getLessonWithDetailsById(Integer id);

    LessonDTO getLessonByGroupIdAndDay(Integer groupId, DayOfWeek day);

    List<LessonDTO> getAllLessons();

    List<LessonDTO> getLessonsByDay(DayOfWeek day);

    List<LessonDTO> getLessonsByGroupId(Integer groupId);

    List<LessonDTO> getLessonsByTeacherId(Integer teacherId);

    List<LessonDTO> getLessonsByClassId(Integer disciplineId);

    void saveLesson(LessonDTO lesson);

    void updateLesson(LessonDTO lesson);

    void deleteLesson(Integer lessonId);
}
