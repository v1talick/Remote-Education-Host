package com.phd.RemoteEducationHost.enteties.enums;

import java.util.HashMap;
import java.util.Map;

public enum LessonType {
    LECTURE, PRACTICAL_LESSON, LABORATORY_LESSON;

    public static LessonType getEnum(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }
        s = s.toUpperCase().replaceAll("\\s+", "_"); // Replace spaces with underscores
        return LessonType.valueOf(s);
    }

    @Override
    public String toString() {
        Map<LessonType, String> lessonTypeStringMap = Map.of(
                LECTURE, "Lecture", PRACTICAL_LESSON, "Practical lesson", LABORATORY_LESSON, "Laboratory lesson");
        return lessonTypeStringMap.get(this);
    }
}
