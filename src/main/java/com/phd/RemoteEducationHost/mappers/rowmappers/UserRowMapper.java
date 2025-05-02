package com.phd.RemoteEducationHost.mappers.rowmappers;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("profile_id"));
        user.setBirthdayDate(rs.getDate("birthday_date"));
        user.setCreateAt(rs.getDate("creation_date"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("firstname"));
        user.setLastName(rs.getString("lastname"));
        user.setPassword(rs.getString("encrypted_password"));
        return user;
    }
}
