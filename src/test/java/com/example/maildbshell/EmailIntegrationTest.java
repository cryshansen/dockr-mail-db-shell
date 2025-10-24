package com.example.maildbshell;

import com.example.maildbshell.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailIntegrationTest {

    @Autowired
    private EmailService emailService;

    @Test
    void shouldSendTestEmail() {
        // When
        emailService.sendTestEmail("test@local.dev");

        // Then - no exceptions = success
        System.out.println("✅ Email sent successfully to MailHog.");
    }
}
