package com.capstoneproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * This is the Main Java class where the spring Boot application starts.
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendApplication {

    /**
     * Run method.
     * @param args String type arguments.
     */
    private void run(final String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    /**
     * This is the main method for the Spring boot class.
     * @param args are the command line arguments.
     */
    public static void main(final String[] args) {
        new BackendApplication().run(args);
    }
}
