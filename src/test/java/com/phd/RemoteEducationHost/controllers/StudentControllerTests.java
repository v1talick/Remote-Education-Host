package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
// @Sql(scripts = "classpath:testdb/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class StudentControllerTests {
    private static final String BASE_URL = "/students";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetAllStudents() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        // Deserialize JSON to List<StudentDTO>
        List<StudentDTO> students = objectMapper.readValue(jsonResponse, new TypeReference<List<StudentDTO>>() {
        });

        assertFalse(students.isEmpty());
        assertEquals(1, students.get(0).getId());
    }

    @Test
    @WithMockUser
    public void testGetStudentById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        // Deserialize JSON to StudentDTO
        StudentDTO studentDTO = objectMapper.readValue(jsonResponse, StudentDTO.class);

        assertEquals(1, studentDTO.getId());
    }

    @Test
    @WithMockUser
    public void testGetStudentByIdNotFound() throws Exception {
        mockMvc.perform(get(BASE_URL + "/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testSaveStudent() throws Exception {
        StudentDTO studentDTO = TestObjectDTOsFactory.getStudentDTO();
        mockMvc.perform(post(BASE_URL + "/admin-panel")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void testUpdateStudent() throws Exception {
        StudentDTO studentDTO = TestObjectDTOsFactory.getStudentDTO();
        studentDTO.setId(1);
        GroupDTO groupDTO = TestObjectDTOsFactory.getGroupDTO();
        groupDTO.setId(6);
        studentDTO.setGroupDTO(groupDTO);
        mockMvc.perform(put(BASE_URL + "/admin-panel")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk());
    }
}
