package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.TaskDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTests {
    private static final String BASE_URL = "/tasks";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails("admin@example.com")
    void saveTaskTest() throws Exception {
        TaskDTO taskDTO = TestObjectDTOsFactory.getTaskDTO();
        String taskJson = objectMapper.writeValueAsString(taskDTO);

        // JSON part
        MockMultipartFile taskPart = new MockMultipartFile(
                "task",
                "",
                "application/json",
                taskJson.getBytes()
        );
        // Optional file part (or skip if testing without file)
        MockMultipartFile filePart = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "Hello file".getBytes()
        );

        mockMvc.perform(multipart(BASE_URL)
                        .file(taskPart)
//                        .file(filePart)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin@example.com")
    void updateTaskTest() throws Exception {
        TaskDTO taskDTO = TestObjectDTOsFactory.getTaskDTO();
        taskDTO.setId(2);
        String taskJson = objectMapper.writeValueAsString(taskDTO);

        // JSON part
        MockMultipartFile taskPart = new MockMultipartFile(
                "task",
                "",
                "application/json",
                taskJson.getBytes()
        );

        mockMvc.perform(multipart(BASE_URL)
                        .file(taskPart)
//                        .file(filePart)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(request -> {
                            request.setMethod("PUT"); // Override to PUT
                            return request;
                        }))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin@example.com")
    void getTasksByGroupTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/group/1"))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        List<TaskDTO> listDTOs = objectMapper.readValue(json, new TypeReference<List<TaskDTO>>() {
        });

        assertFalse(listDTOs.isEmpty());
        assertEquals("Implement a sorting algorithm.", listDTOs.get(0).getDescription());
    }
}
