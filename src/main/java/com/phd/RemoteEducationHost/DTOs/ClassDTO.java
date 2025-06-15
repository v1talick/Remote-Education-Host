package com.phd.RemoteEducationHost.DTOs;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private TeacherDTO teacher;
    @NotNull
    private DisciplineDTO discipline;
    @NotNull
    private GroupDTO group;
    @NotNull
    private boolean isActive;
    private Date startedAt;
}