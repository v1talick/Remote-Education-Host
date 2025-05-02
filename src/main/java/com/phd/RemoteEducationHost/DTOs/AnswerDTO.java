package com.phd.RemoteEducationHost.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private Integer id;
    private TaskDTO task;
    private StudentDTO student;
    private Integer grade;
    private String filePath;
    private Date taskDeliveryTime;
}
