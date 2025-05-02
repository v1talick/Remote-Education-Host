package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.LessonDTO;
import com.phd.RemoteEducationHost.enteties.Lesson;

public class LessonMapper {
    public static Lesson mapToEntity(LessonDTO dto) {
        Lesson entity = new Lesson();
        if (dto == null) {
            return null;
        }

        entity.setId(dto.getId());
        entity.setAClass(ClassMapper.mapToEntity(dto.getAClass()));
        entity.setLessonLink(dto.getLessonLink());
        entity.setLessonType(dto.getLessonType());
        entity.setDayOfWeek(dto.getDayOfWeek());
        entity.setLessonNumber(dto.getLessonNumber());

        return entity;
    }

    public static LessonDTO mapToDTO(Lesson entity) {
        LessonDTO DTO = new LessonDTO();
        if (entity == null) {
            return null;
        }

        DTO.setId(entity.getId());
        DTO.setAClass(ClassMapper.mapToDTO(entity.getAClass()));
        DTO.setLessonLink(entity.getLessonLink());
        DTO.setLessonType(entity.getLessonType());
        DTO.setDayOfWeek(entity.getDayOfWeek());
        DTO.setLessonNumber(entity.getLessonNumber());

        return DTO;
    }
}
