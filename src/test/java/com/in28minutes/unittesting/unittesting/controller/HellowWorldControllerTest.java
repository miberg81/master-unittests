package com.in28minutes.unittesting.unittesting.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value=HellowWorldController.class)
public class HellowWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloWorld_basic() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
            .get("/hello-world")
            .accept(MediaType.APPLICATION_JSON);

        final MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk()) //status=200
                .andExpect(content().string("Hello World"))
                .andReturn();

        //when response is simple we check content in andExpect...
        //assertEquals("Hello World", mvcResult.getResponse().getContentAsString());
    }

}
