package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.enteties.enums.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDTO {
    private Integer id;
    private DayOfWeek dayOfWeek;
    private Integer lessonNumber;
    private ClassDTO aClass;
    private LessonType lessonType;
    private String lessonLink;
}
