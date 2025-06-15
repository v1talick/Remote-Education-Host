package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    private Integer id;
    private Teacher teacher;
    private Discipline discipline;
    private Group group;
    private boolean isActive;
    private Date startedAt;
}
