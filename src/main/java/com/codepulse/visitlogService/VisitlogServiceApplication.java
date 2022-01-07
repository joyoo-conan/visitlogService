package com.codepulse.visitlogService;

import com.codepulse.visitlogService.Controller.ClientController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VisitlogServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(ClientController.class);

	public static void main(String[] args) {
		SpringApplication.run(VisitlogServiceApplication.class, args);

		logger.info(String.format("visitlogServiceApplication started"));
	}
}
