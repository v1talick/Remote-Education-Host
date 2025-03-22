package com.phd.RemoteEducationHost.DTOs;

import com.phd.RemoteEducationHost.enteties.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDTO {
    int id;
    String name;
    DepartmentDTO department;
}
