package com.dimakar.cinemaservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NameConfig {
    @Bean
    public String name1() {
        return "Sokolniki Cinema";
    }

    @Bean
    public String name2() {
        return "Space Cinema";
    }

    @Bean
    public int type() {
        return 1;
    }
}
