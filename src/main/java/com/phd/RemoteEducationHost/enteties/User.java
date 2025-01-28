package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    int id;
    String email;
    String password;
    String firstName;
    String lastName;
    Date createAt;
    Date birthdayDate;
}
