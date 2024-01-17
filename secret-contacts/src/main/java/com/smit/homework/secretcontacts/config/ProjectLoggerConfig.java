package com.smit.homework.secretcontacts.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.smit.homework.secretcontacts.SecretContactsApplication;
import com.smit.homework.secretcontacts.model.ProjectLogger;
import com.smit.homework.secretcontacts.util.LogBackLogger;


/**
 * Configuration class for creating the ProjectLogger bean.
 * 
 * 
 * <p>
 * <strong>Author:</strong> Robert Kris Laur
 * </p>
 */
@Configuration
public class ProjectLoggerConfig {

    @Bean
    ProjectLogger projectLogger() {
        LogBackLogger logger = new LogBackLogger(SecretContactsApplication.class);

        return logger;
    }
}
