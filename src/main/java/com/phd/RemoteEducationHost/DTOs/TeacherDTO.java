package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.enums.Role;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;

import java.util.Date;
import java.util.List;

public class TeacherDTO extends UserDTO {
    Integer id;
    ScienceDegree scienceDegree;
    Department department;

    public TeacherDTO(Integer id, String email, String firstName, String lastName, Date createAt, Date birthdayDate, List<Role> roles, ScienceDegree scienceDegree, Department department) {
        super(id, email, firstName, lastName, createAt, birthdayDate, roles);
        this.scienceDegree = scienceDegree;
        this.department = department;
    }
}
