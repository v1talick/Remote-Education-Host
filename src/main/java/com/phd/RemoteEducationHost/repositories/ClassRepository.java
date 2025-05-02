package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Class;

import java.util.List;
import java.util.Optional;

public interface ClassRepository {
    Class getClassById(Integer id);

    Class getClassWithDetailsById(Integer id);

    List<Class> getAllClasses();

    List<Class> getClassesByTeacherId(Integer teacherId);

    List<Class> getClassesByGroupId(Integer groupId);

    void saveClass(Class aClass);

    void updateClass(Class aClass);

    void deleteClass(Integer classId);
}
