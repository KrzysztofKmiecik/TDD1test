package workshop.springb.testing.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class GreetControllerTestAcceptance {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should Greet->200")
    public void should_Greet() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/greet")
                        .contentType("application/json")
                        .param("isFormal", "true"))

                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}