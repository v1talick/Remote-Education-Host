package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Specialty {
    int id;
    String name;
    Department department;

    public Specialty(int id) {
        this.id = id;
    }
}
