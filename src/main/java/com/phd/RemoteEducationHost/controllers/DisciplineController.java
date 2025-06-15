package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.services.DisciplineService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
@RequiredArgsConstructor
public class DisciplineController {
    private final DisciplineService disciplineService;

    @GetMapping
    public ResponseEntity<List<DisciplineDTO>> getAllDisciplines() {
        List<DisciplineDTO> disciplines = disciplineService.getAllDisciplines();
        return ResponseEntity.ok(disciplines);
    }

    @GetMapping("{id}")
    public ResponseEntity<DisciplineDTO> getDisciplineById(@PathVariable Integer id) {
        DisciplineDTO disciplineDTO = disciplineService.getDisciplineById(id);
        return ResponseEntity.ok(disciplineDTO);
    }

    @PostMapping("/admin-panel")
    public ResponseEntity createDiscipline(@RequestBody @Valid DisciplineDTO disciplineDTO) {
        disciplineService.saveDiscipline(disciplineDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/admin-panel")
    public ResponseEntity updateDiscipline(@RequestBody @Valid DisciplineDTO disciplineDTO) {
        disciplineService.updateDiscipline(disciplineDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin-panel/{id}")
    public ResponseEntity deleteDisciplineById(@PathVariable Integer id) {
        disciplineService.deleteDisciplineById(id);

        return ResponseEntity.ok().build();
    }
}
