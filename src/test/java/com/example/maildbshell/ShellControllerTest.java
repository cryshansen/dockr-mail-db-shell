package com.example.maildbshell;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.maildbshell.service.EmailService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShellController.class)
public class ShellControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private EmailService emailService;

    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from Shell app!"));
    }
    
    
    @Test
    public void testOpenAiKeyEndpoint() throws Exception {
        mockMvc.perform(get("/openaikey"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Key length")));
    }
    
}
