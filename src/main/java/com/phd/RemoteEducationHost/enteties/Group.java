package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    Integer id;
    Specialty specialty;
    String name;
    Date creationDate;

    public Group(Integer id) {
        this.id = id;
    }
}
