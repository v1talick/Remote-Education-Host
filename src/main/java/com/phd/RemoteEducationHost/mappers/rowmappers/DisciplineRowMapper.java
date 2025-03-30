package com.phd.RemoteEducationHost.mappers.rowmappers;

import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.enteties.Discipline;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DisciplineRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Discipline discipline = new Discipline();
        discipline.setId(rs.getInt("discipline_id"));
        discipline.setName(rs.getString("discipline_name"));
        discipline.setDescription(rs.getString("description"));

        return discipline;
    }
}
