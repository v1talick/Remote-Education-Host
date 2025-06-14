package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.LessonDTO;
import com.phd.RemoteEducationHost.TestObjectDTOsFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LessonControllerTests {
    private static final String BASE_URL = "/lessons";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails(value = "admin@example.com")
    void testGetGroupLessons() throws Exception {
        int groupId = 1;

        MvcResult mvcResult = mockMvc.perform(get(BASE_URL+"/group/" + groupId))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        // Deserialize JSON to List<LessonDTO>
         List<LessonDTO> lessons = objectMapper.readValue(jsonResponse, new TypeReference<List<LessonDTO>>() {});
         assertFalse(lessons.isEmpty());
         boolean isAnotherGroups = lessons.stream().anyMatch(lesson -> lesson.getAClass().getGroup().getId() != groupId);
         assertFalse(isAnotherGroups, "Lessons belong to different groups");
    }

    @Test
    @WithUserDetails(value = "admin@example.com")
    void testGetTeacherLessons() throws Exception {
        int teacherId = 4;

        MvcResult mvcResult = mockMvc.perform(get(BASE_URL+"/teacher/" + teacherId))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        // Deserialize JSON to List<LessonDTO>
        List<LessonDTO> lessons = objectMapper.readValue(jsonResponse, new TypeReference<List<LessonDTO>>() {});
        assertFalse(lessons.isEmpty());
        boolean isAnotherTeacher = lessons.stream().anyMatch(lesson -> lesson.getAClass().getTeacher().getId() != teacherId);
        assertFalse(isAnotherTeacher, "Lessons belong to different teachers");
    }

    @Test
    @WithUserDetails("admin@example.com")
    void saveLesson() throws Exception {
        LessonDTO lessonDTO = TestObjectDTOsFactory.getLessonDTO();
        String lessonJson = objectMapper.writeValueAsString(lessonDTO);

        mockMvc.perform(post(BASE_URL + "/admin-panel")
                        .contentType("application/json")
                        .content(lessonJson))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin@example.com")
    void updateLesson() throws Exception {
        LessonDTO lessonDTO = TestObjectDTOsFactory.getLessonDTO();
        lessonDTO.setId(1);
        String lessonJson = objectMapper.writeValueAsString(lessonDTO);

        mockMvc.perform(put(BASE_URL + "/admin-panel")
                        .contentType("application/json")
                        .content(lessonJson))
                .andExpect(status().isOk());
    }
}
