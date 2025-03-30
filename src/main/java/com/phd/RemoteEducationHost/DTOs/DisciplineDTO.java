package com.phd.RemoteEducationHost.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineDTO {
    Integer id;
    String name;
    String description;
}
