package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.LessonDTO;
import com.phd.RemoteEducationHost.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/group/{id}")
    public ResponseEntity<List<LessonDTO>> getLessonsByGroupId(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonService.getLessonsByGroupId(id));
    }

    @GetMapping("teacher/{id}")
    public ResponseEntity<List<LessonDTO>> getLessonsByTeacherId(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonService.getLessonsByTeacherId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonService.getLessonWithDetailsById(id));
    }

    @PostMapping("/admin-panel")
    public ResponseEntity<Void> createLesson(@RequestBody LessonDTO lessonDTO) {
        lessonService.saveLesson(lessonDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/admin-panel")
    public ResponseEntity<Void> updateLesson(@RequestBody LessonDTO lessonDTO) {
        lessonService.updateLesson(lessonDTO);
        return ResponseEntity.status(200).build();
    }
}
