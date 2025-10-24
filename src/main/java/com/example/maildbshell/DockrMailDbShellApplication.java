package com.example.maildbshell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockrMailDbShellApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockrMailDbShellApplication.class, args);
        System.out.println("Shell app running!");
    }
}
