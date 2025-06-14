package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.AnswerDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnswerControllerTests {
    private static final String BASE_URL = "/answers";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        objectMapper.setDateFormat(sdf);
    }

    //task created by teacher
    @Test
    @WithUserDetails("bob.johnson@example.com")
    @Sql(statements = "DELETE FROM answers WHERE task=2")
    void saveAnswer() throws Exception {
        AnswerDTO answerDTO = TestObjectDTOsFactory.getAnswerDTO();
        String taskJson = objectMapper.writeValueAsString(answerDTO);

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
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("arthur.johnson@example.com")
    void saveAnswerUnauthorized() throws Exception {
        AnswerDTO answerDTO = TestObjectDTOsFactory.getAnswerDTO();
        String taskJson = objectMapper.writeValueAsString(answerDTO);

        // JSON part
        MockMultipartFile taskPart = new MockMultipartFile(
                "task",
                "",
                "application/json",
                taskJson.getBytes()
        );

        mockMvc.perform(multipart(BASE_URL)
                        .file(taskPart)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("admin@example.com")
    void updateAnswerTest() throws Exception {
        AnswerDTO answerDTO = TestObjectDTOsFactory.getAnswerDTO();
        answerDTO.setId(1);
        answerDTO.setGrade(66);
        String taskJson = objectMapper.writeValueAsString(answerDTO);

        // JSON part
        MockMultipartFile taskPart = new MockMultipartFile(
                "task",
                "",
                "application/json",
                taskJson.getBytes()
        );

        mockMvc.perform(multipart(BASE_URL)
                        .file(taskPart)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(request -> {
                            request.setMethod("PUT"); // Override to PUT
                            return request;
                        }))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin@example.com")
    void getAnswerById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/2"))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        AnswerDTO answerDTO = objectMapper.readValue(jsonResponse, AnswerDTO.class);

        assertEquals(2, answerDTO.getId());
    }

    @Test
    @WithUserDetails("admin@example.com")
    void getAnswerByTaskId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/task/1"))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        AnswerDTO[] answerDTOs = objectMapper.readValue(jsonResponse, AnswerDTO[].class);

        assertEquals(1, answerDTOs[0].getTask().getId());
    }

    @Test
    @WithUserDetails("dave.miller@example.com")
    void getAnswerByIdUnauthorized() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/2"))
                .andExpect(status().isUnauthorized())
                .andReturn();;
    }
}
