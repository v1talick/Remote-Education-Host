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
    int id;
    DayOfWeek dayOfWeek;
    int lessonNumber;
    Class aClass;
    LessonType lessonType;
    String lessonLink;
}
