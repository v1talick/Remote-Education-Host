package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.DepartmentDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DepartmentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void getDepartmentsTest() throws Exception {
        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void getDepartmentsTestById() throws Exception {
        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void getDepartmentsTestById_NotExists() throws Exception {
        mockMvc.perform(get("/departments/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    void testCreateDepartment() throws Exception {
        DepartmentDTO dto = TestObjectDTOsFactory.getDepartmentDTO();

        // Convert DTO to JSON
        String json = new ObjectMapper().writeValueAsString(dto);

        mockMvc.perform(post("/departments/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated()); // Expect HTTP 201
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    void testCreateDepartment_DepartmentNameAlreadyExists() throws Exception {
        DepartmentDTO dto = TestObjectDTOsFactory.getDepartmentDTO();
        dto.setName("Computer Science"); // a name that already exists
        // Convert DTO to JSON
        String json = new ObjectMapper().writeValueAsString(dto);

        mockMvc.perform(post("/departments/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()); // Expect HTTP 400
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    void testUpdateDepartment() throws Exception {
        DepartmentDTO dto = TestObjectDTOsFactory.getDepartmentDTO();
        dto.setId(2); // Assuming this ID exists
        // Convert DTO to JSON
        dto.setName("Updated Department Name");
        String json = new ObjectMapper().writeValueAsString(dto);

        mockMvc.perform(put("/departments/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()); // Expect HTTP 200
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    void testUpdateDepartment_DepartmentNameAlreadyExists() throws Exception {
        DepartmentDTO dto = TestObjectDTOsFactory.getDepartmentDTO();
        dto.setId(2); // Assuming this ID exists
        dto.setName("Computer Science"); // a name that already exists
        // Convert DTO to JSON
        String json = new ObjectMapper().writeValueAsString(dto);

        mockMvc.perform(put("/departments/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()); // Expect HTTP 400
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    void testDeleteDepartment_BadRequest() throws Exception {
        mockMvc.perform(delete("/departments/admin-panel/1")) // Assuming this ID exists
                .andExpect(status().isBadRequest()); // Expect HTTP 200
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    void testDeleteDepartment() throws Exception {
        mockMvc.perform(delete("/departments/admin-panel/6")) // Assuming this ID exists
                .andExpect(status().isOk()); // Expect HTTP 200
    }

    @Test
    void testDeleteDepartment_Unauthorized() throws Exception {
        mockMvc.perform(delete("/departments/admin-panel/1")) // Assuming this ID exists
                .andExpect(status().isForbidden()); // Expect HTTP 200
    }
}
