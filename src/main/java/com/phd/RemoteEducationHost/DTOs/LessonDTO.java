package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.enteties.Class;
import com.phd.RemoteEducationHost.enteties.enums.LessonType;

import java.time.DayOfWeek;

public class LessonDTO {
    private Integer id;
    private DayOfWeek dayOfWeek;
    private Integer lessonNumber;
    private ClassDTO aClass;
    private LessonType lessonType;
    private String lessonLink;
}
