package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.TeacherDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.TeacherCreationDTO;
import com.phd.RemoteEducationHost.repositories.TeacherRepository;
import com.phd.RemoteEducationHost.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<TeacherDTO> teacherDTOList = teacherService.getAllTeachers();

        return new ResponseEntity<>(teacherDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Integer id) {
        return new ResponseEntity<>(teacherService.getTeacherById(id), HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<TeacherDTO>> getTeacherByDepartment(@PathVariable Integer departmentId) {
        return ResponseEntity.ok(teacherService.getAllTeachersFromDepartment(departmentId));
    }

    @PostMapping("/admin-panel")
    public ResponseEntity<Void> saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        teacherService.saveTeacher(teacherDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admin-panel")
    public ResponseEntity<Void> updateTeacher(@RequestBody TeacherDTO teacherDTO) {
        teacherService.updateTeacher(teacherDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin-panel/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
