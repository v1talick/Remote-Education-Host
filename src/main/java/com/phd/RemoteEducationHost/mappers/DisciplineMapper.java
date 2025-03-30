package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.enteties.Discipline;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisciplineMapper {

    public static Discipline disciplineDTOtoDiscipline(DisciplineDTO disciplineDTO) {
        Discipline discipline = new Discipline();
        discipline.setId(discipline.getId());
        discipline.setName(disciplineDTO.getName());
        discipline.setDescription(disciplineDTO.getDescription());

        return discipline;
    }

    public static DisciplineDTO disciplineToDisciplineDTO(Discipline discipline) {
        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(discipline.getId());
        disciplineDTO.setName(discipline.getName());
        disciplineDTO.setDescription(discipline.getDescription());

        return disciplineDTO;
    }
}
