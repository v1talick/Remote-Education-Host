package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Discipline;
import com.phd.RemoteEducationHost.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class DisciplineRepositoryImpl implements DisciplineRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Optional<Discipline> getDisciplineById(int id) {
        String sql = "select * from disciplines where discipline_id = ?";
        try {
            return Optional.of((Discipline) jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> new Discipline(rs.getInt("discipline_id"),
                            rs.getString("discipline_name"),
                            rs.getString("description")), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        String sql = "select * from disciplines";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Discipline(
                rs.getInt("discipline_id"),
                rs.getString("discipline_name"),
                rs.getString("description")));
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
    public void deleteDiscipline(int disciplineId) {
        String sql = "delete from disciplines where discipline_id=?";
        jdbcTemplate.update(sql, disciplineId);
    }
}
