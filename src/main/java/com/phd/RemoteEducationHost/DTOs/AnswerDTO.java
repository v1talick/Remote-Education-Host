package com.phd.RemoteEducationHost.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private Integer id;
    private TaskDTO task;
    private StudentDTO student;
    private Integer grade;
    private String filePath;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate taskDeliveryTime;
}
