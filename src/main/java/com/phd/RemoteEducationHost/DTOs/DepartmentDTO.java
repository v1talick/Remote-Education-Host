package com.phd.RemoteEducationHost.DTOs;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    Integer id;
    String name;
    String description;
    Date createdAt;
}
