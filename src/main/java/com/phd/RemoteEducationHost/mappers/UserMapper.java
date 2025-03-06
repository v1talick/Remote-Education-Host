package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {
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
    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setCreateAt(user.getCreateAt());
        userDTO.setBirthdayDate(user.getBirthdayDate());
        userDTO.setRoles(userDTO.getRoles());

        return userDTO;
    }

    public static User userToUserDTO(UserCreationDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(user.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCreateAt(userDTO.getCreateAt());
        user.setBirthdayDate(userDTO.getBirthdayDate());
        user.setRoles(user.getRoles());

        return user;
    }
}
