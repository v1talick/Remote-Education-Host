package com.phd.RemoteEducationHost.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private Integer id;
    @NotNull
    private SpecialtyDTO specialtyDTO;
    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    private Date creationDate;
}
