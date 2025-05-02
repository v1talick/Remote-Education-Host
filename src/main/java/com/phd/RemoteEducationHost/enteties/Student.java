package com.phd.RemoteEducationHost.enteties;

import com.phd.RemoteEducationHost.enteties.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {
    Group group;

    public Student(Integer id, String email, String password, String firstName, String lastName, Date createAt, Date birthdayDate, List<Role> roles, Group group) {
        super(id, email, password, firstName, lastName, createAt, birthdayDate, roles);
        this.group = group;
    }
}
