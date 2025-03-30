package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    Integer id;
    Task task;
    Student student;
    Integer grade;
    String filePath;
    Date taskDeliveryTime;
}
