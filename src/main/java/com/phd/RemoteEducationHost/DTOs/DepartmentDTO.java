package com.phd.RemoteEducationHost.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    @NotNull
    @Size(min = 1, max = 255)
    private String description;
    private Date createdAt;
}
