package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Discipline;
import com.phd.RemoteEducationHost.mappers.DisciplineMapper;
import com.phd.RemoteEducationHost.mappers.rowmappers.DisciplineRowMapper;
import com.phd.RemoteEducationHost.repositories.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class DisciplineRepositoryImpl implements DisciplineRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DisciplineRowMapper disciplineMapper;
    @Override
    public Discipline getDisciplineById(Integer id) {
        String sql = "select * from disciplines where discipline_id = ?";

        return (Discipline) jdbcTemplate.queryForObject(sql, disciplineMapper, id);
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        String sql = "select * from disciplines";
        return jdbcTemplate.query(sql, disciplineMapper);
    }

    @Override
    public void saveDiscipline(Discipline discipline) {
        String sql = "insert into disciplines (discipline_name, description) values (?, ?)";
        jdbcTemplate.update(sql, discipline.getName(), discipline.getDescription());
    }

    @Override
    public void updateDiscipline(Discipline discipline) {
        String sql = "update disciplines set discipline_name=?, description=? where discipline_id=?";
        jdbcTemplate.update(sql, discipline.getName(), discipline.getDescription(), discipline.getId());
    }

    @Override
    public void deleteDiscipline(Integer disciplineId) {
        String sql = "delete from disciplines where discipline_id=?";
        jdbcTemplate.update(sql, disciplineId);
    }
}
