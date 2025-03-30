package com.phd.RemoteEducationHost.DTOs.creationDTOs;

import com.phd.RemoteEducationHost.enteties.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDTO {
    String email;
    String password;
    String firstName;
    String lastName;
    Date createAt;
    Date birthdayDate;
    List<Role> roles;
}
