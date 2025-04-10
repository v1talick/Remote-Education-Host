package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.DepartmentDTO;
import com.phd.RemoteEducationHost.repositories.DepartmentRepository;
import com.phd.RemoteEducationHost.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api-v1/admin-panel")
@RequiredArgsConstructor
public class AdminPanelController {
    private final DepartmentService departmentService;

//    @GetMapping(path = "student-roles")
//    public List<User> studentRoles() {
//        userService.getUsersWithoutStudentRole();
//    }
}
