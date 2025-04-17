package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/students";

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetAllStudents() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        // Deserialize JSON to List<StudentDTO>
        List<StudentDTO> students = objectMapper.readValue(jsonResponse, new TypeReference<List<StudentDTO>>() {});

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

//    @Test
//    @WithMockUser
//    public void testSaveStudent() throws Exception{
//        mockMvc.perform(post(BASE_URL + "/admin-panel")).
//    }
}
