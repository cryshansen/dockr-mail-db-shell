package com.example.maildbshell;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShellController {


    @Value("${openai.apiKey:}")
    private String openAiKey;

	
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Shell app!";
    }
    
    @GetMapping("/openaikey")
    public String showKey() {
    	System.out.println("Key length: " + (openAiKey != null && !openAiKey.isEmpty() ? openAiKey.length() : "Key not set"));
        return "Key length: " + (openAiKey != null && !openAiKey.isEmpty() ? openAiKey.length() : "Key not set");
    }
    
}
