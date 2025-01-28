package com.phd.RemoteEducationHost.repositories;

import java.util.List;
import java.util.Optional;

public interface ClassRepository {
    Optional<Class> getClassById(int id);
    List<Class> getAllClasses();
    List<Class> getClassesByTeacherId(int teacherId);
    List<Class> getClassesByGroupId(int groupId);
    void saveClass(Class aClass);
    void updateClass(Class aClass);
    void deleteClass(int classId);
}
