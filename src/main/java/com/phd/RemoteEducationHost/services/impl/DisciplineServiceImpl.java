package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.DepartmentDTO;
import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.enteties.Discipline;
import com.phd.RemoteEducationHost.mappers.DisciplineMapper;
import com.phd.RemoteEducationHost.repositories.DisciplineRepository;
import com.phd.RemoteEducationHost.services.DepartmentService;
import com.phd.RemoteEducationHost.services.DisciplineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineRepository disciplineRepository;
    @Override
    public Optional<DisciplineDTO> getDisciplineById(int id) {
        return disciplineRepository.getDisciplineById(id).map(DisciplineMapper::disciplineToDisciplineDTO);
    }

    @Override
    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineRepository.getAllDisciplines().stream().map(DisciplineMapper::disciplineToDisciplineDTO).toList();
    }

    @Override
    public void saveDiscipline(DisciplineDTO disciplineDTO) {
        disciplineRepository.saveDiscipline(DisciplineMapper.disciplineDTOtoDiscipline(disciplineDTO));
    }

    @Override
    public void updateDiscipline(DisciplineDTO disciplineDTO) {
        disciplineRepository.updateDiscipline(DisciplineMapper.disciplineDTOtoDiscipline(disciplineDTO));
    }

    @Override
    public void deleteDisciplineById(int id) {
        disciplineRepository.deleteDiscipline(id);
    }
}
