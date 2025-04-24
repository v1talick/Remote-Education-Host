package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.creationDTOs.UserCreationDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/authentication";

    @Test
    public void authenticationTest() throws Exception {
        UserCreationDTO userCreationDTO = TestObjectDTOsFactory.getUserCreationDTO();

        // Perform registration
        mockMvc.perform(post(BASE_URL + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreationDTO)))
                .andExpect(status().isCreated());

        // Perform login and check JWT
        MvcResult result = mockMvc.perform(post(BASE_URL + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreationDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // Extract JSON response
        String jsonResponse = result.getResponse().getContentAsString();

        // Parse JSON to extract JWT
        JsonNode responseNode = objectMapper.readTree(jsonResponse);
        String jwt = responseNode.get("jwt").asText();

        // Assert JWT is not blank
        assertNotNull(jwt, "JWT should not be null");
        assertFalse(jwt.trim().isEmpty(), "JWT should not be empty");
    }
}
