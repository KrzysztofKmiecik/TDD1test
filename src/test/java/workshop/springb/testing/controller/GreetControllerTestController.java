package workshop.springb.testing.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import workshop.springb.testing.model.Response;
import workshop.springb.testing.service.GreetService;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
class GreetControllerTestController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetService greetService;

    @Test
    @DisplayName("should greet -> 200")
    public void should_greet() throws Exception {
        Mockito.when(greetService.greet("World", true)).thenReturn(new Response("Hello, World", LocalDateTime.now()));
        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .contentType("application/json")
                        .param("isFormal", "true"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.greeting").value("Hello, World"));
    }

}