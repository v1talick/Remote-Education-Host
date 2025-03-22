package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Discipline;

import java.util.List;
import java.util.Optional;

public interface DisciplineRepository {
    Discipline getDisciplineById(int id);
    List<Discipline> getAllDisciplines();
    void saveDiscipline(Discipline discipline);
    void updateDiscipline(Discipline discipline);
    void deleteDiscipline(int disciplineId);
}
