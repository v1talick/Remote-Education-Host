package com.phd.RemoteEducationHost.repositories.impl;

import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Optional<User> getUserById(int id) {
        String sql = "select * from profiles where id = ?";
        try {
            return Optional.of((User) jdbcTemplate.query(sql, (rs, rowNum) -> new User(rs.getInt("profile_id"),
                    rs.getString("email"),
                    rs.getString("encrypted_password"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getDate("creation_date"),
                    rs.getDate("birthday_date")), id));
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
                rs.getDate("birthday_date")));
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into profiles (profile_id, email, encrypted_password, firstname, lastname, creation_date, birthday_date) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getCreateAt(), user.getBirthdayDate());
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
