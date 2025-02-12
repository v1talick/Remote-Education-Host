package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.enteties.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("profile_id"));
        user.setBirthdayDate(rs.getDate("birthday_date"));
        user.setCreateAt(rs.getDate("creation_date"));
        user.setEmail(rs.getString("email"));
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setPassword("encrypted_password");
        return user;
    }
}
