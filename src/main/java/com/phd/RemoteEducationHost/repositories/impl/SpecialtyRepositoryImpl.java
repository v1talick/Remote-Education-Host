package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.enteties.Specialty;
import com.phd.RemoteEducationHost.mappers.rowmappers.SpecialtyRowMapper;
import com.phd.RemoteEducationHost.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpecialtyRepositoryImpl implements SpecialtyRepository {

    private final JdbcTemplate jdbcTemplate;

    private final SpecialtyRowMapper specialtyRowMapper;

    @Override
    public Specialty getSpecialtyById(Integer id) {
        String sql = "SELECT * FROM specialties WHERE specialty_id = ?";

        return (Specialty) jdbcTemplate.queryForObject(sql, specialtyRowMapper, id);
    }

    @Override
    public Specialty getSpecialtyWithDetailsById(Integer id) {
        String sql = "select * from specialties s join departments d 0n s.department=d.department_id where s.specialty_id=?";

        return (Specialty) jdbcTemplate.queryForObject(sql, specialtyRowMapper, id);
    }

    @Override
    public List<Specialty> getAllSpecialties() {
        String sql = "select * from specialties";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Specialty(
                rs.getInt("specialty_id"),
                rs.getString("specialty_name"),
                new Department(rs.getInt("department"))));
    }

    @Override
    public void saveSpecialty(Specialty specialty) {
        String sql = "insert into specialties (specialty_name, department) values (?, ?)";
        jdbcTemplate.update(sql, specialty.getName(), specialty.getDepartment().getId());
    }

    @Override
    public void updateSpecialty(Specialty specialty) {
        String sql = "update specialties set specialty_name=?, department=? where specialty_id=?";
        jdbcTemplate.update(sql, specialty.getName(), specialty.getDepartment().getId(), specialty.getId());
    }

    @Override
    public void deleteSpecialty(Integer id) {
        String sql = "delete from specialties where specialty_id=?";
        jdbcTemplate.update(sql, id);
    }
}
