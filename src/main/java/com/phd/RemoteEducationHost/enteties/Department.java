package com.phd.RemoteEducationHost.enteties;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer id;
    private String name;
    private String description;
    private Date createdAt;

    public Department(Integer id) {
        this.id = id;
    }

}
