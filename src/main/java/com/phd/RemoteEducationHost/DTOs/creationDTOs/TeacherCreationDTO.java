package com.phd.RemoteEducationHost.DTOs.creationDTOs;

import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.enums.Role;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class TeacherCreationDTO extends UserCreationDTO {
    @NotNull
    private ScienceDegree scienceDegree;
    @NotNull
    private Department department;

    public TeacherCreationDTO(String email, String password, String firstName, String lastName, Date createAt, Date birthdayDate, List<Role> roles, ScienceDegree scienceDegree, Department department) {
        super(email, password, firstName, lastName, createAt, birthdayDate, roles);
        this.scienceDegree = scienceDegree;
        this.department = department;
    }
}
