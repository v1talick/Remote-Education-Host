package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.exceptions.InvalidArgumentException;
import com.phd.RemoteEducationHost.mappers.DisciplineMapper;
import com.phd.RemoteEducationHost.repositories.DisciplineRepository;
import com.phd.RemoteEducationHost.services.DisciplineService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineRepository disciplineRepository;
    @Override
    public DisciplineDTO getDisciplineById(Integer id) {
        return DisciplineMapper.disciplineToDisciplineDTO(disciplineRepository.getDisciplineById(id));
    }

    @Override
    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineRepository.getAllDisciplines().stream().map(DisciplineMapper::disciplineToDisciplineDTO).toList();
    }

    @Override
    public void saveDiscipline(DisciplineDTO disciplineDTO) {
        try {
            disciplineRepository.saveDiscipline(DisciplineMapper.disciplineDTOtoDiscipline(disciplineDTO));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Discipline with such name already exists");
        }
    }

    @Override
    public void updateDiscipline(DisciplineDTO disciplineDTO) {
        try {
            disciplineRepository.updateDiscipline(DisciplineMapper.disciplineDTOtoDiscipline(disciplineDTO));
        } catch (DuplicateKeyException e) {
            throw new InvalidArgumentException("Discipline with such name already exists");
        }
    }

    @Override
    public void deleteDisciplineById(Integer id) {
        try {
            disciplineRepository.deleteDiscipline(id);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidArgumentException("Discipline with such id does is used in other entities");
        }
    }
}
