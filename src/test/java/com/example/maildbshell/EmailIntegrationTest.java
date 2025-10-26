package com.example.maildbshell;

import com.example.maildbshell.service.EmailService;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest

class EmailIntegrationTest {

    @Autowired
    private EmailService emailService;

    @Test
    void shouldSendTestEmail() throws InterruptedException {
        // When
        emailService.sendTestEmail("test@local.dev");

        /* new code included to test the endpoint recieved the message*/
        
        // Allow a small delay for MailHog to process
        Thread.sleep(1500);

        // Determine host based on environment
        String mailhogHost = System.getenv("SPRING_MAIL_HOST");
        if (mailhogHost == null || mailhogHost.isEmpty()) {
            mailhogHost = "localhost"; // fallback for local
        }

        String mailhogApi = "http://" + mailhogHost + ":8025/api/v2/messages";
        // Create RestTemplate that understands text/json
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(org.springframework.http.MediaType.valueOf("text/json")));
        restTemplate.getMessageConverters().add(0, converter);

        // Act
        ResponseEntity<Map> response = restTemplate.getForEntity(mailhogApi, Map.class);

        // Assert
        assertEquals(200, response.getStatusCodeValue(), "MailHog API should respond with 200 OK");

        Map body = response.getBody();
        assertNotNull(body, "MailHog response should not be null");
        assertTrue(body.containsKey("items"), "MailHog response should contain 'items'");

        
        // Then - no exceptions = success
        System.out.println("✅ Email sent successfully to MailHog.");
    }
}