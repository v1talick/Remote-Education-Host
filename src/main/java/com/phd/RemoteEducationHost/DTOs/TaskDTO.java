package com.phd.RemoteEducationHost.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    Integer id;
    @JsonProperty("class")
    ClassDTO aClass;
    String description;
    String filePath;
    Date deadline;
}
