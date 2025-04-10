package com.phd.RemoteEducationHost.enteties;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    Integer id;
    String name;
    String description;
    Date createdAt;

    public Department(Integer id) {
        this.id = id;
    }

}
