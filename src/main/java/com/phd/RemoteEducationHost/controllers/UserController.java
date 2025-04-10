package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.security.responses.AuthResponse;
import com.phd.RemoteEducationHost.security.jwt.JwtProvider;
import com.phd.RemoteEducationHost.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserCreationDTO userCreationDTO) {
        return new ResponseEntity<>(userService.login(userCreationDTO), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserCreationDTO userCreationDTO) {
        AuthResponse authResponse = userService.saveUser(userCreationDTO);
        if (!authResponse.getStatus()) {
            return new ResponseEntity(authResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/admin-panel/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
