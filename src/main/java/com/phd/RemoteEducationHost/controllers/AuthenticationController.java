package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.facades.AuthenticationFacade;
import com.phd.RemoteEducationHost.security.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserCreationDTO userCreationDTO) {
        return new ResponseEntity<>(authenticationFacade.login(userCreationDTO), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserCreationDTO userCreationDTO) {
        AuthResponse authResponse = authenticationFacade.register(userCreationDTO);
        if (authResponse.getStatus()) {
            return new ResponseEntity(authResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
    }
}
