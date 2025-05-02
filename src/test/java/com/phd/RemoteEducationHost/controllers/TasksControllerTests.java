package com.phd.RemoteEducationHost.controllers;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Sql(scripts = "classpath:testdb/delete_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class TasksControllerTests {
}

