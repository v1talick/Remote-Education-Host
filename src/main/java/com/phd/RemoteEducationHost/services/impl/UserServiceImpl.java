package com.phd.RemoteEducationHost.services.impl;

import com.phd.RemoteEducationHost.DTOs.UserDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.enteties.User;
import com.phd.RemoteEducationHost.mappers.UserMapper;
import com.phd.RemoteEducationHost.repositories.UserRepository;
import com.phd.RemoteEducationHost.security.jwt.JwtProvider;
import com.phd.RemoteEducationHost.security.responses.AuthResponse;
import com.phd.RemoteEducationHost.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    @Override
    public UserDTO getUserById(Integer id) {
        return UserMapper.userToUserDTO(userRepository.getUserById(id));
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.getAllUsers().stream().map(UserMapper::userToUserDTO).toList();
    }

    @Override
    public AuthResponse saveUser(UserCreationDTO userCreationDTO) {
        AuthResponse authResponse = new AuthResponse();

        String originalPassword = userCreationDTO.getPassword();
        userCreationDTO.setCreateAt(new Date());
        userCreationDTO.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
        User user = UserMapper.userCreationDTOToUser(userCreationDTO);
        try {
            userRepository.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            throw new BadCredentialsException("Email address already in use");
        }
        Authentication authentication;
        authentication = authenticate(userCreationDTO.getEmail(), originalPassword);

        String jwt = JwtProvider.generateToken(authentication);
        authResponse.setJwt(jwt);
        authResponse.setStatus(true);
        authResponse.setMessage("User successfully registered");

        return authResponse;
    }

    // TODO: move to AuthenticationFacade
    @Override
    public AuthResponse login(UserCreationDTO userCreationDTO) {
        AuthResponse authResponse = new AuthResponse();
        Authentication authentication;
        authentication = authenticate(userCreationDTO.getEmail(), userCreationDTO.getPassword());
        authResponse.setMessage("User successfully logged in");
        authResponse.setStatus(true);
        authResponse.setJwt(JwtProvider.generateToken(authentication));

        return authResponse;
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteUser(id);
    }

    private Authentication authenticate(String username, String password) {

        System.out.println(username+"---++----"+password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        System.out.println("Sig in in user details"+ userDetails);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid username and password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }
}
