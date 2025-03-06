package com.phd.RemoteEducationHost.repositories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.enteties.enums.Role;
import com.phd.RemoteEducationHost.mappers.UserMapper;
import com.phd.RemoteEducationHost.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;



    @Override
    public Optional<User> getUserById(int id) {
        String sql = "select * from profiles where profile_id = ?";
        try {
            User user = (User) jdbcTemplate.queryForObject(sql, userMapper, id);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserWithRolesById(int id) {
        String sql = "select * from profiles where profile_id = ?";
        try {
            User user = (User) jdbcTemplate.queryForObject(sql, userMapper, id);
            user.setRoles(getUserRoles(user.getId()));
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from profiles";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(rs.getInt("profile_id"),
                rs.getString("email"),
                rs.getString("encrypted_password"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("creation_date"),
                rs.getDate("birthday_date"), new LinkedList<>()));
    }

    private List<Role> getUserRoles(int userId) {
        List<Role> roles = new LinkedList<>();
        String sql = "SELECT EXISTS(SELECT 1 FROM students WHERE student_id=?)";
        if (jdbcTemplate.queryForObject(sql, Boolean.class, userId)) {
            roles.add(Role.STUDENT);
        }
        sql = "SELECT EXISTS(SELECT 1 FROM teachers WHERE teacher_id=?)";
        if (jdbcTemplate.queryForObject(sql, Boolean.class, userId)) {
            roles.add(Role.TEACHER);
        }
        sql = "SELECT EXISTS(SELECT 1 FROM admins WHERE admin_id=?)";
        if (jdbcTemplate.queryForObject(sql, Boolean.class, userId)) {
            roles.add(Role.ADMIN);
        }
        return roles;
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into profiles (email, encrypted_password, firstname, lastname, creation_date, birthday_date) values (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getCreateAt(), user.getBirthdayDate());
    }

    @Override
    public void updateUser(User user) {
        String sql = "update profiles set email=?, encrypted_password=?, firstname=?, lastname=?, creation_date=?, birthday_date=? where profile_id=?";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getCreateAt(), user.getBirthdayDate(), user.getId());
    }

    @Override
    public void deleteUser(int userId) {
        String sql = "delete from profiles where profile_id=?";
        jdbcTemplate.update(sql, userId);
    }
}
