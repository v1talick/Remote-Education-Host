package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.SpecialtyDTO;
import com.phd.RemoteEducationHost.enteties.Specialty;
import com.phd.RemoteEducationHost.mappers.SpecialtyMapper;
import com.phd.RemoteEducationHost.repositories.SpecialtyRepository;
import com.phd.RemoteEducationHost.services.SpecialtyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService{
    private final SpecialtyRepository specialtyRepository;
    @Override
    public SpecialtyDTO getSpecialtyById(Integer id) {
        return SpecialtyMapper.specialtyToSpecialtyDTO(specialtyRepository.getSpecialtyById(id));
    }

    @Override
    public List<SpecialtyDTO> getAllSpecialties() {
        return specialtyRepository.getAllSpecialties().stream().map(SpecialtyMapper::specialtyToSpecialtyDTO).toList();
    }

    @Override
    public void saveSpecialty(SpecialtyDTO specialtyDTO) {
        specialtyRepository.saveSpecialty(SpecialtyMapper.specialtyDTOtoSpecialty(specialtyDTO));
    }

    @Override
    public void updateSpecialty(SpecialtyDTO specialtyDTO) {
        specialtyRepository.updateSpecialty(SpecialtyMapper.specialtyDTOtoSpecialty(specialtyDTO));
    }

    @Override
    public void deleteSpecialty(Integer id) {
        specialtyRepository.deleteSpecialty(id);
    }
}
