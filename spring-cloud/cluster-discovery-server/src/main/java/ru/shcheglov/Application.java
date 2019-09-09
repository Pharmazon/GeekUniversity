package ru.shcheglov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * GET http://localhost:8761
 * https://github.com/Pharmazon/SpringCloud.git
 * POST http://localhost:8761/actuator/refresh
 */

@EnableEurekaServer
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

