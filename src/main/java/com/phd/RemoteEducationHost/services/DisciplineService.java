package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.enteties.Discipline;

import java.util.List;
import java.util.Optional;

public interface DisciplineService {
    DisciplineDTO getDisciplineById(Integer id);

    List<DisciplineDTO> getAllDisciplines();

    void saveDiscipline(DisciplineDTO disciplineDTO);

    void updateDiscipline(DisciplineDTO disciplineDTO);

    void deleteDisciplineById(Integer id);
}
