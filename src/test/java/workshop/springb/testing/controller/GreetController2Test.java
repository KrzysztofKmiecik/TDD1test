package workshop.springb.testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import workshop.springb.testing.model.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class GreetController2Test {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void shouldGreet() {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .param("isFormal", "true"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        workshop.springb.testing.model.Response response = objectMapper.readValue(json, Response.class);
        assertAll(
                () -> assertEquals("Hello, World!", response.getGreeting())


        );

    }


    @Test
    @SneakyThrows
    void shouldGreetHi() {
        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .param("isFormal", "false"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


}