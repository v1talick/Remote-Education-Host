package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.enteties.Discipline;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DisciplineMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Discipline discipline = new Discipline();
        discipline.setId(rs.getInt("discipline_id"));
        discipline.setName(rs.getString("discipline_name"));
        discipline.setDescription(rs.getString("description"));

        return discipline;
    }

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
