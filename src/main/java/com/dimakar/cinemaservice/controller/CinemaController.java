package com.dimakar.cinemaservice.controller;

import com.dimakar.cinemaservice.dto.Cinema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaController {
    private final List<Cinema> cinemaList = new ArrayList<>();

    {
        cinemaList.add(new Cinema(1, "Sokolniki", "Stromynka", true));
        cinemaList.add(new Cinema(2, "VDNH", "Mira", false));
    }

    @GetMapping("/cinema")
    public List<Cinema> getCinemaList() {
        return cinemaList;
    }

    @GetMapping("/cinema/{id}")
    public Cinema getCinemaById(@PathVariable int id) {
        if (cinemaList.stream().anyMatch(cinema -> cinema.getId().equals(id))) {
            return cinemaList.stream().filter(cinema -> cinema.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema with id = '" + id + "' is not found"));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema with id ='" + id + "' is not found'");
        }
    }

    @PostMapping("/cinema")
    public Cinema createCinema(@RequestBody Cinema newCinema) {
        if (newCinema.getId() == null || newCinema.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and name mustn't be null");
        }
        if (cinemaList.stream().anyMatch(cinema -> cinema.getId().equals(newCinema.getId()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cinema with id = '" + newCinema.getId() + "' already exists");
        } else {
            cinemaList.add(newCinema);
            return newCinema;
        }
    }

    @DeleteMapping("/cinema/{id}")
    public void createCinema(@PathVariable int id) {
        if (cinemaList.stream().anyMatch(cinema -> cinema.getId().equals(id))) {
            cinemaList.remove(cinemaList.stream().filter(cinema -> cinema.getId().equals(id))
                    .findFirst().orElseThrow());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema with id ='" + id + "' is not found'");
        }
    }


}
