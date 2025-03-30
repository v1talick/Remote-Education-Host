package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.SpecialtyDTO;
import com.phd.RemoteEducationHost.enteties.Specialty;

import java.util.List;
import java.util.Optional;

public interface SpecialtyService {
    public SpecialtyDTO getSpecialtyById(Integer id);
    public List<SpecialtyDTO> getAllSpecialties();
    void saveSpecialty(SpecialtyDTO specialtyDTO);
    void updateSpecialty(SpecialtyDTO specialtyDTO);
    void deleteSpecialty(Integer id);
}
