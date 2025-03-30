package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discipline {
    Integer id;
    String name;
    String description;
}
