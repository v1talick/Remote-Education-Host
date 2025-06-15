package com.phd.RemoteEducationHost.enteties;

import com.phd.RemoteEducationHost.enteties.enums.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private Integer id;
    private DayOfWeek dayOfWeek;
    private Integer lessonNumber;
    private Class aClass;
    private LessonType lessonType;
    private String lessonLink;
}
