package com.phd.RemoteEducationHost.facades.impl;

import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.facades.AuthenticationFacade;
import com.phd.RemoteEducationHost.security.jwt.JwtProvider;
import com.phd.RemoteEducationHost.security.responses.AuthResponse;
import com.phd.RemoteEducationHost.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponse register(UserCreationDTO userCreationDTO) {
        AuthResponse authResponse = new AuthResponse();

        String originalPassword = userCreationDTO.getPassword();
        userCreationDTO.setCreateAt(new Date());
        userCreationDTO.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));

        userService.saveUser(userCreationDTO);

        Authentication authentication = authenticate(userCreationDTO.getEmail(), originalPassword);

        String jwt = JwtProvider.generateToken(authentication);
        authResponse.setJwt(jwt);
        authResponse.setStatus(true);
        authResponse.setMessage("User successfully registered");

        return authResponse;
    }

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

    private Authentication authenticate(String username, String password) {

        System.out.println(username + "---++----" + password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        System.out.println("Sig in in user details" + userDetails);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username and password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }
}
