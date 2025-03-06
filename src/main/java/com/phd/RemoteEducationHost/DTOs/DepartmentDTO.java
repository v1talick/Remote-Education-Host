package com.phd.RemoteEducationHost.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    int id;
    String name;
    String description;
    Date createdAt;
}
