package com.phd.RemoteEducationHost.enteties.enums;

public enum ScienceDegree {
    AssociateOfScience,
    BachelorOfScience,
    MasterOfScience,
    DoctorOfPhilosophy,
    DoctorOfScience,
    ProfessionalDoctorate,
    None;
    public static ScienceDegree getEnum(String s) {
        s = s.replaceAll("\\s+","");
        s = s.replaceAll("of", "Of");

        return ScienceDegree.valueOf(s);
    }
}
