package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.enteties.enums.Role;
import com.phd.RemoteEducationHost.mappers.rowmappers.UserRowMapper;
import com.phd.RemoteEducationHost.repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;


    @Override
    public User getUserById(Integer id) {
        String sql = "select * from profiles where profile_id = ?";

        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "select * from profiles where email = ?";

        return jdbcTemplate.queryForObject(sql, userRowMapper, email);
    }

    @Override
    public User getUserWithRolesById(Integer id) {
        String sql = "select * from profiles where profile_id = ?";

        User user = jdbcTemplate.queryForObject(sql, userRowMapper, id);
        user.setRoles(getUserRoles(user.getId()));
        return user;
    }

    @Override
    public User getUserByEmailWithRoles(@NotNull String email) {
        User user = getUserByEmail(email);
        user.setRoles(getUserRoles(user.getId()));

        return user;
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
    public void deleteUser(Integer userId) {
        String sql = "delete from profiles where profile_id=?";
        jdbcTemplate.update(sql, userId);
    }

    private List<Role> getUserRoles(Integer userId) {
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
}
