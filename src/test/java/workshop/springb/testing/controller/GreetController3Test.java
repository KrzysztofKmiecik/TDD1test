package workshop.springb.testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import workshop.springb.testing.model.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class GreetController3Test {

    @Autowired
    private MockMvc mockMvc;
@Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void shouldGreetHello() {
        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .param("isFormal", "true"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.greeting").value("Hello, World!"));
    }

    @Test
    @SneakyThrows
    void sghouldGreetHi_status200() {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .param("isFormal", "false"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.greeting").value("Hi, World!")))
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        Response response = objectMapper.readValue(json, Response.class);

        assertAll(
                () -> assertEquals("Hi, World!", response.getGreeting())
        );
    }
}