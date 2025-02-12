package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User{
    Group group;

    public Student(int id, String email, String password, String firstName, String lastName, Date createAt, Date birthdayDate, Group group) {
        super(id, email, password, firstName, lastName, createAt, birthdayDate);
        this.group = group;
    }
}
