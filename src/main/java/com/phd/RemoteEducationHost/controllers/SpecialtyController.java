package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.SpecialtyDTO;
import com.phd.RemoteEducationHost.services.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @GetMapping
    private ResponseEntity<List<SpecialtyDTO>> getAllSpecialties() {
        return ResponseEntity.ok(specialtyService.getAllSpecialties());
    }

    @GetMapping("{id}")
    private ResponseEntity<SpecialtyDTO> getSpecialtyById(@PathVariable Integer id) {
        return ResponseEntity.ok(specialtyService.getSpecialtyById(id));
    }

    @PostMapping("/admin-panel")
    private ResponseEntity createSpecialty(@RequestBody SpecialtyDTO specialtyDTO) {
        specialtyService.saveSpecialty(specialtyDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/admin-panel")
    private ResponseEntity updateSpecialty(@RequestBody SpecialtyDTO specialtyDTO) {
        specialtyService.updateSpecialty(specialtyDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin-panel/{id}")
    private ResponseEntity deleteSpecialty(@PathVariable Integer id) {
        specialtyService.deleteSpecialty(id);

        return ResponseEntity.ok().build();
    }
}
