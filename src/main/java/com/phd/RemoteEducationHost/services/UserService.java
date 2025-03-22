package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.security.responses.AuthResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO getUserById(int id);
    List<UserDTO> getAllUser();
    AuthResponse saveUser(UserCreationDTO userCreationDTO);
    AuthResponse login(UserCreationDTO userCreationDTO);
    void deleteUserById(int id);
}
