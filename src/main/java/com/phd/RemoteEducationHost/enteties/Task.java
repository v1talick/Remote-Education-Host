package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    Integer id;
    Class aClass;
    String description;
    String filePath;
    Date deadline;
}
