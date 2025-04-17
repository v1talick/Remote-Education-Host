package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.SpecialtyDTO;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.SpecialtyMapper;
import com.phd.RemoteEducationHost.repositories.SpecialtyRepository;
import com.phd.RemoteEducationHost.services.SpecialtyService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // TODO: make unique (name, department) for specialty
        try {
            specialtyRepository.saveSpecialty(SpecialtyMapper.specialtyDTOtoSpecialty(specialtyDTO));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Specialty with such name already exists");
        }
    }

    @Override
    public void updateSpecialty(SpecialtyDTO specialtyDTO) {
        try {
            specialtyRepository.updateSpecialty(SpecialtyMapper.specialtyDTOtoSpecialty(specialtyDTO));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Specialty with such name already exists");
        }
    }

    @Override
    public void deleteSpecialty(Integer id) {
        try {
            specialtyRepository.deleteSpecialty(id);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Specialty with such id does is used in other entities");
        }
    }
}
