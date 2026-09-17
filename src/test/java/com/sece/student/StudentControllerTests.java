package com.sece.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sece.student.entity.Studententity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testIndexPageLoadsWithModel() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attributeExists("students"));
    }

    @Test
    void testSaveStudentViaForm() throws Exception {
        mockMvc.perform(post("/save")
                        .param("name", "Test User")
                        .param("department", "Physics")
                        .param("age", "22")
                        .param("username", "testuser")
                        .param("password", "testpass"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testRestGetAllStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    void testRestAddAndGetStudent() throws Exception {
        Studententity newStudent = new Studententity(0, "Rohan Verma", "Civil", 23, "rohanv", "pass456");

        String responseBody = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Rohan Verma")))
                .andReturn().getResponse().getContentAsString();

        Studententity created = objectMapper.readValue(responseBody, Studententity.class);

        mockMvc.perform(get("/students/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.department", is("Civil")));
    }

    @Test
    void testAuthRegisterAndLogin() throws Exception {
        Studententity user = new Studententity(0, "Pooja Hegde", "CSE", 21, "pooja_" + System.currentTimeMillis(), "secret123");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Registration Successful")));

        Studententity loginReq = new Studententity();
        loginReq.setUsername(user.getUsername());
        loginReq.setPassword("secret123");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Login Successful")));
    }
}
