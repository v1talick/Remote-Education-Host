package com.phd.RemoteEducationHost.enteties.enums;

import java.util.Map;

public enum ScienceDegree {
    ASSOCIATE_OF_SCIENCE,
    BACHELOR_OF_SCIENCE,
    MASTER_OF_SCIENCE,
    DOCTOR_OF_PHILOSOPHY,
    DOCTOR_OF_SCIENCE,
    PROFESSIONAL_DOCTORATE,
    NONE;

    public static ScienceDegree getEnum(String s) {
        s = s.toUpperCase();
        s = s.replaceAll("\\s+", "_");

        return ScienceDegree.valueOf(s);
    }

    public String toString() {
        Map<ScienceDegree, String> map = Map.of(
                ASSOCIATE_OF_SCIENCE, "Associate of Science",
                BACHELOR_OF_SCIENCE, "Bachelor of Science",
                MASTER_OF_SCIENCE, "Master of Science",
                DOCTOR_OF_PHILOSOPHY, "Doctor of Philosophy",
                DOCTOR_OF_SCIENCE, "Doctor of Science",
                PROFESSIONAL_DOCTORATE, "Professional Doctorate",
                NONE, "None"
        );
        return map.get(this);
    }
}
