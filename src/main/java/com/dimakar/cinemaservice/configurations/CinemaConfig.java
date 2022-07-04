package com.dimakar.cinemaservice.configurations;

import com.dimakar.cinemaservice.dto.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinemaConfig {
    @Bean
    public Cinema cinema1(@Qualifier("name1") String name, @Autowired int type) {
        return new Cinema(type, name, "Stromynka", true);
    }

    @Bean
    public Cinema cinema2(@Qualifier("name2") String name, @Autowired int type) {
        return new Cinema(type, name, "VDNH", true);
    }
}
