package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.DisciplineDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Sql(scripts = "classpath:testdb/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class DisciplineControllerTests {
    private static final String BASE_URL = "/disciplines";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void getDisciplinesTest() throws Exception {
        MvcResult result = mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        // Deserialize JSON to List<GroupDto>
        List<DisciplineDTO> disciplineDTOS = objectMapper.readValue(jsonResponse, new TypeReference<List<DisciplineDTO>>() {
        });

        assertFalse(disciplineDTOS.isEmpty());
        assertEquals("Algorithms and Data Structures", disciplineDTOS.get(0).getName());
        assertEquals("Processing of digital signals in electrical systems.", disciplineDTOS.get(1).getDescription());
    }

    @Test
    @WithMockUser
    public void getDisciplinesByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        DisciplineDTO disciplineDTO = objectMapper.readValue(jsonResponse, DisciplineDTO.class);

        assertEquals("Algorithms and Data Structures", disciplineDTO.getName());
    }

    @Test
    @WithMockUser
    public void getDisciplinesByIdNotFoundTest() throws Exception {
        mockMvc.perform(get(BASE_URL + "/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getDisciplinesByIdUnauthorizedTest() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void saveDisciplineTest() throws Exception {
        DisciplineDTO disciplineDTO = TestObjectDTOsFactory.getDisciplineDTO();

        mockMvc.perform(post(BASE_URL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplineDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void saveDisciplineTest_BadRequest() throws Exception {
        DisciplineDTO disciplineDTO = TestObjectDTOsFactory.getDisciplineDTO();
        disciplineDTO.setName("Algorithms and Data Structures");

        mockMvc.perform(post(BASE_URL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplineDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void updateDisciplineTest() throws Exception {
        DisciplineDTO disciplineDTO = TestObjectDTOsFactory.getDisciplineDTO();
        disciplineDTO.setId(3);
        disciplineDTO.setName("Updated Discipline Name");

        mockMvc.perform(put(BASE_URL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplineDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void updateDisciplineTest_BadRequest() throws Exception {
        DisciplineDTO disciplineDTO = TestObjectDTOsFactory.getDisciplineDTO();
        disciplineDTO.setId(3);
        disciplineDTO.setName("Algorithms and Data Structures");

        mockMvc.perform(put(BASE_URL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(disciplineDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "adminUser", roles = {"ADMIN"})
    public void deleteDisciplineTest() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/admin-panel/3"))
                .andExpect(status().isOk());
    }
}