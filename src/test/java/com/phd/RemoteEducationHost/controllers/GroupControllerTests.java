package com.phd.RemoteEducationHost.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phd.RemoteEducationHost.DTOs.GroupDTO;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Sql(scripts = "classpath:testdb/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class GroupControllerTests {
    private static final String BASE_URL = "/groups";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetAllGroups() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        // Deserialize JSON to List<GroupDto>
        List<GroupDTO> groups = objectMapper.readValue(jsonResponse, new TypeReference<List<GroupDTO>>() {
        });

        assertFalse(groups.isEmpty());
        assertEquals("CS-101", groups.get(0).getName());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetGroupById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/2"))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        // Deserialize JSON to GroupDto
        GroupDTO group = objectMapper.readValue(jsonResponse, GroupDTO.class);

        assertEquals(2, group.getId());
        assertEquals("CS-201", group.getName());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetGroupById_NotFound() throws Exception {
        mockMvc.perform(get(BASE_URL + "/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetGroupById_Forbidden() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testSaveGroup() throws Exception {
        String json = objectMapper.writeValueAsString(TestObjectDTOsFactory.getGroupDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testSaveGroup_GroupNameAlreadyExists() throws Exception {
        GroupDTO groupDTO = TestObjectDTOsFactory.getGroupDTO();
        groupDTO.setName("CS-101"); // a name that already exists
        String json = objectMapper.writeValueAsString(groupDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testUpdateGroup() throws Exception {
        GroupDTO groupDTO = TestObjectDTOsFactory.getGroupDTO();
        groupDTO.setId(3); // Assuming this ID exists
        groupDTO.setName("Updated");
        String json = objectMapper.writeValueAsString(groupDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/admin-panel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testDeleteGroup_GroupIsUsed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/admin-panel/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testDeleteGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/admin-panel/7"))
                .andExpect(status().isOk());
    }
}
