package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDTO> getUserById(int id);
    List<UserDTO> getAllUser();
    void saveUser(UserCreationDTO userCreationDTO);
    void deleteUserById(int id);
}
