package com.phd.RemoteEducationHost.DTOs.creationDTOs;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.enteties.Group;
import com.phd.RemoteEducationHost.enteties.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class StudentCreationDTO extends UserCreationDTO{
    GroupDTO groupDTO;

    public StudentCreationDTO(int id, String email, String password, String firstName, String lastName, Date createAt, Date birthdayDate, List<Role> roles, GroupDTO groupDTO) {
        super(id, email, password, firstName, lastName, createAt, birthdayDate, roles);
        this.groupDTO = groupDTO;
    }
}
