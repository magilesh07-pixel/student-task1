package com.sece.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sece.student.entity.Course;
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
class CourseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCourses() throws Exception {
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));
    }

    @Test
    void testCreateAndGetCourse() throws Exception {
        Course newCourse = new Course(0, "Cloud Computing & DevOps", "Computer Science", 6, 30000.0);

        String response = mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCourse)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.courseName", is("Cloud Computing & DevOps")))
                .andExpect(jsonPath("$.fees", is(30000.0)))
                .andReturn().getResponse().getContentAsString();

        Course created = objectMapper.readValue(response, Course.class);

        mockMvc.perform(get("/courses/" + created.getCourseId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.department", is("Computer Science")));
    }

    @Test
    void testUpdateCourse() throws Exception {
        Course course = new Course(0, "Machine Learning", "AI & DS", 5, 28000.0);

        String response = mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Course created = objectMapper.readValue(response, Course.class);

        Course updatedData = new Course();
        updatedData.setCourseName("Advanced Machine Learning & Deep Learning");
        updatedData.setFees(35000.0);
        updatedData.setDuration(6);
        updatedData.setDepartment("AI & DS");

        mockMvc.perform(put("/courses/" + created.getCourseId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName", is("Advanced Machine Learning & Deep Learning")))
                .andExpect(jsonPath("$.fees", is(35000.0)));
    }

    @Test
    void testDeleteCourse() throws Exception {
        Course course = new Course(0, "Cyber Security Fundamentals", "IT", 3, 15000.0);

        String response = mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Course created = objectMapper.readValue(response, Course.class);

        mockMvc.perform(delete("/courses/" + created.getCourseId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("deleted successfully")));

        mockMvc.perform(get("/courses/" + created.getCourseId()))
                .andExpect(status().isNotFound());
    }
}
