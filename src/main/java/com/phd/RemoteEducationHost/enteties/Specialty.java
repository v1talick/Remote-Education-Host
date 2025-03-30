package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialty {
    Integer id;
    String name;
    Department department;
}

