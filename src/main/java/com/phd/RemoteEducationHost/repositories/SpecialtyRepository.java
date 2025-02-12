package com.phd.RemoteEducationHost.repositories;

import com.phd.RemoteEducationHost.enteties.Specialty;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SpecialtyRepository {
    Optional<Specialty> getSpecialtyById(int id);
    Optional<Specialty> getSpecialtyWithDetailsById(int id);
    List<Specialty> getAllSpecialties();
    void saveSpecialty(Specialty specialty);
    void updateSpecialty(Specialty specialty);
    void deleteSpecialty(int id);
}
