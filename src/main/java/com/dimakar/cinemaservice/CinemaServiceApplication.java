package com.dimakar.cinemaservice;

import com.dimakar.cinemaservice.dto.Cinema;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaServiceApplication.class, args);
    }


    @Bean
    public Cinema printCinema(@Qualifier("cinema2") Cinema cinema) {
        System.out.println(cinema);
        return cinema;
    }

}
