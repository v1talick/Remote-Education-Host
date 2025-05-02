package com.phd.RemoteEducationHost.DTOs;

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