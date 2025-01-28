package com.phd.RemoteEducationHost.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    int id;
    Task task;
    Student student;
    int grade;
    String filePath;
    Date taskDeliveryTime;
}
