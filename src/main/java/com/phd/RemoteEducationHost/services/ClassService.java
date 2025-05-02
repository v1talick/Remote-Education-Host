package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.ClassDTO;
import com.phd.RemoteEducationHost.enteties.Class;

import java.util.List;

public interface ClassService {
    ClassDTO getClassById(Integer id);

    ClassDTO getClassWithDetailsById(Integer id);

    List<ClassDTO> getAllClasses();

    List<ClassDTO> getClassesByTeacherId(Integer teacherId);

    List<ClassDTO> getClassesByGroupId(Integer groupId);

    void saveClass(ClassDTO classDTO);

    void updateClass(ClassDTO classDTO);

    void deleteClass(Integer classId);
}
