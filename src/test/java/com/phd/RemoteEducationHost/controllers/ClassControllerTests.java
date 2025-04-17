package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.ClassDTO;
import com.phd.RemoteEducationHost.DTOs.GroupDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/classes";

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetAllClasses() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        // Deserialize JSON to List<ClassDTO>
        List<ClassDTO> classes = objectMapper.readValue(jsonResponse, new TypeReference<List<ClassDTO>>() {});

        assertFalse(classes.isEmpty());
        assertEquals(1, classes.get(0).getId());
    }

    @Test
    @WithMockUser
    public void testGetClassById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        // Deserialize JSON to ClassDTO
        ClassDTO classDTO = objectMapper.readValue(jsonResponse, ClassDTO.class);

        assertNotNull(classDTO);
        assertEquals(1, classDTO.getId());
        assertEquals("Algorithms and Data Structures", classDTO.getDiscipline().getName());
        assertEquals("Carol", classDTO.getTeacher().getFirstName());
    }

    @Test
    @WithMockUser
    public void testGetClassByIdNotFound() throws Exception {
        mockMvc.perform(get(BASE_URL + "/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testGetClassesByGroupId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/group/2"))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        // Deserialize JSON to List<ClassDTO>
        List<ClassDTO> classes = objectMapper.readValue(jsonResponse, new TypeReference<List<ClassDTO>>() {});

        assertFalse(classes.isEmpty());
        assertEquals(1, classes.get(0).getId());
    }

//    @Test
//    @WithMockUser
//    public void testGetClassesByTeacherId() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/teacher/1"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String jsonResponse = mvcResult.getResponse().getContentAsString();
//        // Deserialize JSON to List<ClassDTO>
//        List<ClassDTO> classes = objectMapper.readValue(jsonResponse, new TypeReference<List<ClassDTO>>() {});
//
//        assertFalse(classes.isEmpty());
//        assertEquals(1, classes.get(0).getId());
//    }

    @Test
    @WithMockUser
    public void testCreateClass() throws Exception {
        ClassDTO classDTO = TestObjectDTOsFactory.getClassDTO();
        GroupDTO groupDTO = TestObjectDTOsFactory.getGroupDTO();
        groupDTO.setId(6);

        mockMvc.perform(post(BASE_URL + "/admin-panel")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(classDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void testCreateClass_BadRequest() throws Exception {
        ClassDTO classDTO = TestObjectDTOsFactory.getClassDTO();
        classDTO.setTeacher(null); // Ensure ID is null for creation
        mockMvc.perform(post(BASE_URL + "/admin-panel")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(classDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateClass_Forbidden() throws Exception {
        ClassDTO classDTO = TestObjectDTOsFactory.getClassDTO();
        mockMvc.perform(post(BASE_URL)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(classDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void testUpdateClass() throws Exception {
        ClassDTO classDTO = TestObjectDTOsFactory.getClassDTO();
        classDTO.setId(1); // Ensure ID is set for update
        GroupDTO groupDTO = TestObjectDTOsFactory.getGroupDTO();
        groupDTO.setId(5); // Ensure ID is set for update
        classDTO.setActive(false); // Ensure ID is null for creation
        mockMvc.perform(put(BASE_URL + "/admin-panel")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(classDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void deleteClass() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/admin-panel/1"))
                .andExpect(status().isBadRequest());
    }
}
