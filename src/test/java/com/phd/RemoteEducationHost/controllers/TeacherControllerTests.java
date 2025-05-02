package com.phd.RemoteEducationHost.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.TeacherDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import com.phd.RemoteEducationHost.enteties.enums.ScienceDegree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Sql(scripts = "classpath:testdb/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class TeacherControllerTests {
    private static final String BASE_URL = "/teachers";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetAllTeachers() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetTeacherById() throws Exception {
        mockMvc.perform(get(BASE_URL + "/4"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testGetTeacherByDepartment() throws Exception {
        mockMvc.perform(get(BASE_URL + "/department/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testCreateTeacher() throws Exception {
        TeacherDTO teacherDTO = TestObjectDTOsFactory.getTeacherDTO();
        teacherDTO.setId(5);
        String teacherJson = objectMapper.writeValueAsString(teacherDTO);
        mockMvc.perform(post(BASE_URL + "/admin-panel")
                        .contentType("application/json")
                        .content(teacherJson))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void testUpdateTeacher() throws Exception {
        TeacherDTO teacherDTO = TestObjectDTOsFactory.getTeacherDTO();
        teacherDTO.setId(3);
        teacherDTO.setScienceDegree(ScienceDegree.NONE);
        String teacherJson = objectMapper.writeValueAsString(teacherDTO);
        mockMvc.perform(put(BASE_URL + "/admin-panel")
                        .contentType("application/json")
                        .content(teacherJson))
                .andExpect(status().isOk());
    }
}
