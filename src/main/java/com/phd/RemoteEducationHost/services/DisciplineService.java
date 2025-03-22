package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.enteties.Discipline;

import java.util.List;
import java.util.Optional;

public interface DisciplineService {
    public DisciplineDTO getDisciplineById(int id);
    public List<DisciplineDTO> getAllDisciplines();
    public void saveDiscipline(DisciplineDTO disciplineDTO);
    public void updateDiscipline(DisciplineDTO disciplineDTO);
    public void deleteDisciplineById(int id);
}
