package com.phd.RemoteEducationHost.controllers;

import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.services.GroupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Integer id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @PostMapping("/admin-panel")
    public ResponseEntity createGroup(@RequestBody @Valid GroupDTO groupDTO) {
        groupService.saveGroup(groupDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/admin-panel")
    public ResponseEntity updateGroup(@RequestBody @Valid GroupDTO groupDTO) {
        groupService.updateGroup(groupDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin-panel/{id}")
    public ResponseEntity deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroupById(id);

        return ResponseEntity.ok().build();
    }
}
