package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.mappers.UserMapper;
import com.phd.RemoteEducationHost.repositories.UserRepository;
import com.phd.RemoteEducationHost.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public Optional<UserDTO> getUserById(int id) {
        return userRepository.getUserById(id).map(UserMapper::userCreationDTOToUser);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.getAllUsers().stream().map(UserMapper::userCreationDTOToUser).toList();
    }

    @Override
    public void saveUser(UserCreationDTO userCreationDTO) {
        userCreationDTO.setCreateAt(new Date());
        User user = UserMapper.userCreationDTOToUser(userCreationDTO);
        userRepository.saveUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUser(id);
    }
}
