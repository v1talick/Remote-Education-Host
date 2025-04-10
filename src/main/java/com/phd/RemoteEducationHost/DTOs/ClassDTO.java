package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.DTOs.creationDTOs.TeacherCreationDTO;
import com.phd.RemoteEducationHost.enteties.Discipline;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Teacher;

import java.util.Date;

public class ClassDTO {
    private Integer id;
    private TeacherDTO teacher;
    private Discipline discipline;
    Group group;
    boolean isActive;
    Date startedAt;
}
