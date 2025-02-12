package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    int id;
    String name;
    String description;
    Date createdAt;

    public Department(int id) {
        this.id = id;
    }
}
