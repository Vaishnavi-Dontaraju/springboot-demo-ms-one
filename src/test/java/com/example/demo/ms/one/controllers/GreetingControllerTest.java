package com.example.demo.ms.one.controllers;

import com.example.demo.ms.one.models.GreetingResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGreeting() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get("/greeting")
                        .param("name", "test")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        GreetingResponse greetingResponse = objectMapper.readValue(
                response.getContentAsString(), GreetingResponse.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(greetingResponse.getApplicationId()).startsWith("demo-ms-one-");
        assertThat(greetingResponse.getMessage()).startsWith("Hello, test! Current time is");
    }
}
