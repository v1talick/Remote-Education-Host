package com.phd.RemoteEducationHost.enteties;

import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User{
    ScienceDegree scienceDegree;
    Department department;

    public Teacher(int id, String email, String password, String firstName, String lastName, Date createAt, Date birthdayDate, ScienceDegree scienceDegree, Department department) {
        super(id, email, password, firstName, lastName, createAt, birthdayDate);
        this.scienceDegree = scienceDegree;
        this.department = department;
    }
}
