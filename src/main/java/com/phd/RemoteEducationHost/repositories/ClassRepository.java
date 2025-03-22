package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Class;

import java.util.List;
import java.util.Optional;

public interface ClassRepository {
    Class getClassById(int id);
    Class getClassWithDetailsById(int id);
    List<Class> getAllClasses();
    List<Class> getClassesByTeacherId(int teacherId);
    List<Class> getClassesByGroupId(int groupId);
    void saveClass(Class aClass);
    void updateClass(Class aClass);
    void deleteClass(int classId);
}
