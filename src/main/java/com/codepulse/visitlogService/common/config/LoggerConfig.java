package com.codepulse.visitlogService.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

public class LoggerConfig {

    @Bean
    public Logger logger() {
        Logger logger = LoggerFactory.getLogger(LoggerConfig.class);
        return logger;
    }
}
