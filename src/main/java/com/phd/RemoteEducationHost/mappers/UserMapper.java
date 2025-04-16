package com.phd.RemoteEducationHost.mappers;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper  {
    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        if(user == null) {
            return userDTO;
        }
        userDTO.setId(user.getId());
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setCreateAt(user.getCreateAt());
        userDTO.setBirthdayDate(user.getBirthdayDate());
        userDTO.setRoles(userDTO.getRoles());

        return userDTO;
    }

    public static User userCreationDTOToUser(UserCreationDTO userDTO) {
        User user = new User();
        if(userDTO == null) {
            return user;
        }
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCreateAt(userDTO.getCreateAt());
        user.setBirthdayDate(userDTO.getBirthdayDate());
        user.setRoles(user.getRoles());

        return user;
    }
}
