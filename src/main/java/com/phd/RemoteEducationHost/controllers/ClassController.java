package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.ClassDTO;
import com.phd.RemoteEducationHost.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @GetMapping
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ClassDTO>> getClassGroupById(@PathVariable Integer groupId) {
        return ResponseEntity.ok(classService.getClassesByGroupId(groupId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ClassDTO>> getClassTeacherById(@PathVariable("teacherId") Integer teacherId) {
        return ResponseEntity.ok(classService.getClassesByTeacherId(teacherId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Integer id) {
        return ResponseEntity.ok(classService.getClassWithDetailsById(id));
    }

    @PostMapping("/admin-panel")
    public ResponseEntity<Void> createClass(@RequestBody ClassDTO classDTO) {
        classService.saveClass(classDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/admin-panel/{id}")
    public ResponseEntity<Void> updateClass(@PathVariable Integer id, @RequestBody ClassDTO classDTO) {
        classService.updateClass(classDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin-panel/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Integer id) {
        classService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
