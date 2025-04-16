package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.DTOs.creationDTOs.TeacherCreationDTO;
import com.phd.RemoteEducationHost.enteties.Discipline;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassDTO {
    private Integer id;
    private TeacherDTO teacher;
    private DisciplineDTO discipline;
    private GroupDTO group;
    boolean isActive;
    private Date startedAt;
}