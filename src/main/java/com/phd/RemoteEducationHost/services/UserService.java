package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Integer id);

    UserDTO getUserByUsername(String username);

    List<UserDTO> getAllUser();

    void saveUser(UserCreationDTO userCreationDTO);

    void deleteUserById(Integer id);
}
