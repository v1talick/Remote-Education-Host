package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.LessonDTO;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.LessonMapper;
import com.phd.RemoteEducationHost.repositories.LessonRepository;
import com.phd.RemoteEducationHost.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    @Override
    public LessonDTO getLessonById(Integer id) {
        return LessonMapper.mapToDTO(lessonRepository.getLessonById(id));
    }

    @Override
    public LessonDTO getLessonWithDetailsById(Integer id) {
        return LessonMapper.mapToDTO(lessonRepository.getLessonWithDetailsById(id));
    }

    @Override
    public LessonDTO getLessonByGroupIdAndDay(Integer groupId, DayOfWeek day) {
        return null;
    }

    @Override
    public List<LessonDTO> getAllLessons() {
        return List.of();
    }

    @Override
    public List<LessonDTO> getLessonsByDay(DayOfWeek day) {
        return List.of();
    }

    @Override
    public List<LessonDTO> getLessonsByGroupId(Integer groupId) {
        return lessonRepository.getLessonsByGroupId(groupId)
                .stream()
                .map(LessonMapper::mapToDTO)
                .toList();
    }

    @Override
    public List<LessonDTO> getLessonsByTeacherId(Integer teacherId) {
        return List.of();
    }

    @Override
    public List<LessonDTO> getLessonsByClassId(Integer disciplineId) {
        return List.of();
    }

    @Override
    public void saveLesson(LessonDTO lesson) {
        try {
            lessonRepository.getLessonById(lesson.getId());
        } catch (Exception e) { //TODO: add custom exception
            throw new InvalidArgumentException(e.getMessage());
        }
        lessonRepository.saveLesson(LessonMapper.mapToEntity(lesson));
    }

    @Override
    public void updateLesson(LessonDTO lesson) {
        try {
            lessonRepository.updateLesson(LessonMapper.mapToEntity(lesson));
        } catch (Exception e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    @Override
    public void deleteLesson(Integer lessonId) {
        try {
            lessonRepository.deleteLesson(lessonId);
        } catch (Exception e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }
}
