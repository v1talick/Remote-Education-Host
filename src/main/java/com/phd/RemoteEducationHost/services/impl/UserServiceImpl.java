package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.mappers.UserMapper;
import com.phd.RemoteEducationHost.repositories.UserRepository;
import com.phd.RemoteEducationHost.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDTO getUserById(Integer id) {
        return UserMapper.userToUserDTO(userRepository.getUserById(id));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return UserMapper.userToUserDTO(userRepository.getUserByEmailWithRoles(username));
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.getAllUsers().stream().map(UserMapper::userToUserDTO).toList();
    }

    @Override
    public void saveUser(UserCreationDTO userCreationDTO) {
        User user = UserMapper.userCreationDTOToUser(userCreationDTO);
        try {
            userRepository.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            throw new BadCredentialsException("Email address already in use");
        }
    }


    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteUser(id);
    }
}
