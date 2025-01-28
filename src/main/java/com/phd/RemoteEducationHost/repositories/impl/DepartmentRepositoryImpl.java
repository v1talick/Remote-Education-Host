package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Optional<Department> getDepartmentById(int id) {
        String sql = "select * from departments d where d.department_id=?";
        try {
            return Optional.of((Department) jdbcTemplate.query(sql, (rs, rowNum) ->
                    new Department(rs.getInt("department_id"), rs.getString("department_name")
                            , rs.getString("description"), rs.getDate("created_at"))
                    ,id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        String sql = "select * from departments";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Department(rs.getInt("department_id"), rs.getString("department_name")
                        , rs.getString("description"), rs.getDate("created_at")));
    }

    @Override
    public void saveDepartment(Department department) {
        String sql = "insert into departments (department_id, department_name, description, created_at) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, department.getId(), department.getName(), department.getDescription(), department.getCreatedAt());
    }

    @Override
    public void updateDepartment(Department department) {
        String sql = "update departments set department_name=?, description=?, created_at=? where department_id=?";
        jdbcTemplate.update(sql, department.getId(), department.getName(), department.getDescription(), department.getCreatedAt());
    }

    @Override
    public void deleteDepartment(int departmentId) {
        String sql = "delete from departments where department_id=?";
        jdbcTemplate.update(sql, departmentId);
    }
}
