package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.enteties.enums.LessonType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonDTO {
    private Integer id;
    @NotNull
    private DayOfWeek dayOfWeek;
    @Min(1)
    @Max(6)
    @NotNull
    private Integer lessonNumber;
    @NotNull
    private ClassDTO aClass;
    @NotNull
    private LessonType lessonType;
    private String lessonLink;
}
