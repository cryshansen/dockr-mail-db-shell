package com.example.maildbshell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.maildbshell.service.EmailService;

@RestController
public class ShellController {


    @Value("${openai.apiKey:}")
    private String openAiKey;
    
    @Autowired
    private EmailService emailService;

	
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Shell app!";
    }
    
    @GetMapping("/openaikey")
    public String showKey() {
    	System.out.println("Key length: " + (openAiKey != null && !openAiKey.isEmpty() ? openAiKey.length() : "Key not set"));
        return "Key length: " + (openAiKey != null && !openAiKey.isEmpty() ? openAiKey.length() : "Key not set");
    }
    

    @GetMapping("/sendtestemail")
    public String sendTestEmail() {
        try {
            emailService.sendTestEmail("test@local.dev");
            System.out.println("Test email Sent");
            return "✅ Test email sent successfully!";
        } catch (Exception e) {
        	System.out.println("Test email failed");
            return "❌ Email send failed: " + e.getMessage();
        }
    }
    
}
