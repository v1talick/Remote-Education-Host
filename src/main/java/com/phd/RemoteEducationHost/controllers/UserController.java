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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserCreationDTO user) {
        AuthResponse authResponse = new AuthResponse();
        try {
            Authentication authentication = authenticate(user.getEmail(), user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = JwtProvider.generateToken(authentication);

            authResponse.setJwt(jwt);
            authResponse.setMessage("Success login");
            authResponse.setStatus(true);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            authResponse.setStatus(false);
            authResponse.setMessage("Invalid email or password");
            return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserCreationDTO userCreationDTO) {
        AuthResponse authResponse = new AuthResponse();
        try {
            userCreationDTO.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
            userService.saveUser(userCreationDTO);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userCreationDTO.getEmail(), userCreationDTO.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = JwtProvider.generateToken(authentication);

            authResponse.setJwt(jwt);
            authResponse.setMessage("Success register");
            authResponse.setStatus(true);

            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            authResponse.setMessage("Wrong data");
            authResponse.setStatus(false);
            return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
        }
    }
    private Authentication authenticate(String username, String password) {

        System.out.println(username+"---++----"+password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        System.out.println("Sig in in user details"+ userDetails);

        if(userDetails == null) {
            System.out.println("Sign in details - null" + userDetails);

            throw new BadCredentialsException("Invalid username and password");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            System.out.println("Sign in userDetails - password mismatch"+userDetails);

            throw new BadCredentialsException("Invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
