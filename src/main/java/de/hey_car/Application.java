package de.hey_car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"de.hey_car.services", "de.hey_car.repository", "de.hey_car.entity", "de.hey_car.controllers"})
@EnableJpaRepositories({"de.hey_car.repository"})
public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("Application starting..");
        SpringApplication.run(Application.class, args);
    }
}
