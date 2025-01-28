package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Specialty;

import java.util.List;

public interface SpecialtyRepository {
    Specialty getSpecialtyById(int id);
    Specialty getSpecialtyWithDetailsById(int id);
    List<Specialty> getAllSpecialties();
    void saveSpecialty(Specialty specialty);
    void updateSpecialty(Specialty specialty);
}
