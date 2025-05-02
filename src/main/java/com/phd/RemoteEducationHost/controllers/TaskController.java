package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.TaskDTO;
import com.phd.RemoteEducationHost.facades.TaskFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskFacade taskFacade;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createTask(@RequestPart(value = "file", required = false) MultipartFile file,
                                           @RequestPart(value = "task") TaskDTO taskDTO) {
        taskFacade.createTask(file, taskDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> updateTask(@RequestPart(value = "file", required = false) MultipartFile file,
                                           @RequestPart(value = "task") TaskDTO taskDTO) {
        taskFacade.updateTask(file, taskDTO);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<List<TaskDTO>> getTasksByGroupId(@PathVariable Integer id) {
        return ResponseEntity.ok(taskFacade.getTasksByGroupId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTasksByStudentId(@PathVariable Integer id) {
        return ResponseEntity.ok(taskFacade.getTaskById(id));
    }
}
