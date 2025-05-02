package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.Department;
import com.phd.RemoteEducationHost.mappers.rowmappers.DepartmentRowMapper;
import com.phd.RemoteEducationHost.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DepartmentRowMapper departmentRowMapper;

    @Override
    public Department getDepartmentById(Integer id) {
        String sql = "select * from departments d where d.department_id=?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                        new Department(rs.getInt("department_id"), rs.getString("department_name")
                                , rs.getString("description"), rs.getDate("created_at"))
                , id);
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
        String sql = "insert into departments (department_name, description, created_at) values (?, ?, ?)";
        jdbcTemplate.update(sql, department.getName(), department.getDescription(), department.getCreatedAt());
    }

    @Override
    public void updateDepartment(Department department) {
        String sql = "update departments set department_name=?, description=?, created_at=? where department_id=?";
        jdbcTemplate.update(sql, department.getName(), department.getDescription(), department.getCreatedAt(), department.getId());
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        String sql = "delete from departments where department_id=?";
        jdbcTemplate.update(sql, departmentId);
    }
}
