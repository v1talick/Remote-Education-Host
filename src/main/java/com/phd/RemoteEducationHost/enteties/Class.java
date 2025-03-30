package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    Integer id;
    Teacher teacher;
    Discipline discipline;
    Group group;
    boolean isActive;
    Date startedAt;
}
