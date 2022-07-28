package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8080/students/{studentId}", 3)
                .header("headerName", "headerValue")
                .param("graduate", "true");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.createDate", notNullValue()))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        System.out.println("Returned Response Body: " + body);
    }

    @Test
    public void create() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"Hank\",\n" +
                        "  \"score\": 14.6,\n" +
                        "  \"graduate\": false\n" +
                        "}");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }
}