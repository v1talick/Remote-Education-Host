package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.ClassDTO;
import com.phd.RemoteEducationHost.enteties.*;
import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ClassMapper {
    public static Class mapToEntity(ClassDTO classDTO) {
        Class classEntity = new Class();
        classEntity.setId(classDTO.getId());
        classEntity.setActive(classDTO.isActive());
        classEntity.setStartedAt(classDTO.getStartedAt());
        classEntity.setGroup(GroupMapper.groupDTOtoGroup(classDTO.getGroup()));
        classEntity.setTeacher(TeacherMapper.mapToEntity(classDTO.getTeacher()));
        classEntity.setDiscipline(DisciplineMapper.disciplineDTOtoDiscipline(classDTO.getDiscipline()));

        return classEntity;
    }

    public static ClassDTO mapToDTO(Class classEntity) {
        ClassDTO classDTO = new ClassDTO();
        classDTO.setId(classEntity.getId());
        classDTO.setActive(classEntity.isActive());
        classDTO.setStartedAt(classEntity.getStartedAt());
        classDTO.setDiscipline(DisciplineMapper.disciplineToDisciplineDTO(classEntity.getDiscipline()));
        classDTO.setTeacher(TeacherMapper.mapToDTO(classEntity.getTeacher()));
        classDTO.setGroup(GroupMapper.groupToGroupDTO(classEntity.getGroup()));

        return classDTO;
    }
}
