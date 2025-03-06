package com.phd.RemoteEducationHost.services;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.User;

import java.util.List;

public interface UserService {
    UserDTO getUserById(int id);
    List<User> getAllUser();
    void saveUser(UserCreationDTO userCreationDTO);
    void deleteUserById(int id);
}
