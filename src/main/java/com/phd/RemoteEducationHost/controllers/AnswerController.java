package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.AnswerDTO;
import com.phd.RemoteEducationHost.facades.AnswerFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerFacade answerFacade;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createAnswer(@RequestPart(value = "file", required = false) MultipartFile file,
                                             @RequestPart(value = "task") @Valid AnswerDTO answerDTO) {
        answerFacade.createAnswer(answerDTO, file);
        return ResponseEntity.status(201).build();
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> updateAnswer(@RequestPart(value = "file", required = false) MultipartFile file,
                                             @RequestPart(value = "task") @Valid AnswerDTO answerDTO) {
        answerFacade.updateAnswer(answerDTO, file);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<AnswerDTO>> getAnswersByStudentId(@PathVariable Integer id) {
        return ResponseEntity.ok(answerFacade.getAllAnswersByStudentId(id));
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<List<AnswerDTO>> getAnswersByTaskId(@PathVariable Integer id) {
        return ResponseEntity.ok(answerFacade.getAllAnswersByTaskId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable Integer id) {
        return ResponseEntity.ok(answerFacade.getAnswerById(id));
    }

    @GetMapping("/student/{studentId}/task/{taskId}")
    public ResponseEntity<AnswerDTO> getAnswerByStudentIdAndTaskId(@PathVariable Integer studentId,
                                                                    @PathVariable Integer taskId) {
        return ResponseEntity.ok(answerFacade.getAnswerByStudentIdAndTaskId(studentId, taskId));
    }
}
