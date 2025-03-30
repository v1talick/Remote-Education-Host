package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.enteties.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Integer id;
    String email;
    String firstName;
    String lastName;
    Date createAt;
    Date birthdayDate;
    List<Role> roles;
}
