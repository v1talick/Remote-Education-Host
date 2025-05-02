package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.SpecialtyDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
// @Sql(scripts = "classpath:testdb/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class SpecialtyControllerTests {
    private static final String BASIC_URL = "/specialties";
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void getSpecialtiesTest() throws Exception {
        mockMvc.perform(get(BASIC_URL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void getSpecialty_ByIdTest() throws Exception {
        mockMvc.perform(get(BASIC_URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void getSpecialty_ByIdTest_NotExists() throws Exception {
        mockMvc.perform(get(BASIC_URL + "/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void createSpecialtyTest() throws Exception {
        SpecialtyDTO specialtyDTO = TestObjectDTOsFactory.getSpecialtyDTO();
        // Convert DTO to JSON
        specialtyDTO.setName("New Software Engineering");
        String json = new ObjectMapper().writeValueAsString(specialtyDTO);

        String basicURL = BASIC_URL;
        mockMvc.perform(post(basicURL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void createSpecialtyTest_NameExists() throws Exception {
        SpecialtyDTO specialtyDTO = TestObjectDTOsFactory.getSpecialtyDTO();
        specialtyDTO.setName("Software Engineering"); // Set a name that already exists
        // Convert DTO to JSON
        String json = new ObjectMapper().writeValueAsString(specialtyDTO);

        mockMvc.perform(post(BASIC_URL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void updateSpecialtyTest() throws Exception {
        SpecialtyDTO specialtyDTO = TestObjectDTOsFactory.getSpecialtyDTO();
        specialtyDTO.setId(2); // Set an existing ID
        // Convert DTO to JSON
        String json = new ObjectMapper().writeValueAsString(specialtyDTO);

        mockMvc.perform(put(BASIC_URL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void deleteSpecialtyTest() throws Exception {
        mockMvc.perform(delete(BASIC_URL + "/admin-panel/1"))
                .andExpect(status().isBadRequest());
    }
}
