package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.enteties.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentDTO extends UserDTO{
    GroupDTO groupDTO;

    public StudentDTO(int id, String email, String firstName, String lastName, Date createAt, Date birthdayDate, List<Role> roles, GroupDTO groupDTO) {
        super(id, email, firstName, lastName, createAt, birthdayDate, roles);
        this.groupDTO = groupDTO;
    }
}
