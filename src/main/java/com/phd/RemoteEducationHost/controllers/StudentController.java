package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.StudentCreationDTO;
import com.phd.RemoteEducationHost.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<StudentDTO>> getStudents(@PathVariable Integer groupId) {
        return ResponseEntity.ok(studentService.getAllStudentsByGroupId(groupId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/admin-panel")
    public ResponseEntity<Void> saveStudent(@RequestBody StudentDTO studentDTO) {
        studentService.saveStudent(studentDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admin-panel")
    public ResponseEntity<Void> updateStudent(@RequestBody StudentCreationDTO studentCreationDTO) {
        studentService.updateStudent(studentCreationDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin-panel/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudentById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
