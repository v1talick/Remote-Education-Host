package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.enteties.enums.Role;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO extends UserDTO {
    Integer id;
    ScienceDegree scienceDegree;
    DepartmentDTO departmentDTO;

    public TeacherDTO(Integer id, String email, String firstName, String lastName, Date createAt, Date birthdayDate, List<Role> roles, ScienceDegree scienceDegree, DepartmentDTO department) {
        super(id, email, firstName, lastName, createAt, birthdayDate, roles);
        this.scienceDegree = scienceDegree;
        this.departmentDTO = department;
    }
}
