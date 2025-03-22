package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.enteties.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    int id;
    Specialty specialty;
    String name;
    Date creationDate;
}
