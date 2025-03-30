package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.enteties.Discipline;

import java.util.List;
import java.util.Optional;

public interface DisciplineService {
    public DisciplineDTO getDisciplineById(Integer id);
    public List<DisciplineDTO> getAllDisciplines();
    public void saveDiscipline(DisciplineDTO disciplineDTO);
    public void updateDiscipline(DisciplineDTO disciplineDTO);
    public void deleteDisciplineById(Integer id);
}
