package com.dimakar.cinemaservice.controller;

import com.dimakar.cinemaservice.dto.Cinema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<Cinema> getCinemaById(@PathVariable int id) {
        if (cinemaList.stream().anyMatch(cinema -> cinema.getId().equals(id))) {
            return new ResponseEntity<>(cinemaList.stream().filter(cinema -> cinema.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Cinema with id = '" + id + "' is not found")), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cinema")
    public ResponseEntity<Cinema> createCinema(@RequestBody Cinema newCinema) {
        if (newCinema.getId() == null || newCinema.getName() == null) {
            return new ResponseEntity<>(cinemaList.stream().findFirst()
                    .orElseThrow(() -> new InvalidParameterException("Id and name mustn't be null")), HttpStatus.BAD_REQUEST);
        }
        if (cinemaList.stream().anyMatch(cinema -> cinema.getId().equals(newCinema.getId()))) {
            return new ResponseEntity<>(cinemaList.stream().findFirst()
                    .orElseThrow(() -> new InvalidParameterException("Cinema with id = '" + newCinema.getId() + "' already exists")), HttpStatus.CONFLICT);
        } else {
            cinemaList.add(newCinema);
            return new ResponseEntity<>(newCinema, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/cinema/{id}")
    public ResponseEntity createCinema(@PathVariable int id) {
        if (cinemaList.stream().anyMatch(cinema -> cinema.getId().equals(id))) {
            cinemaList.remove(cinemaList.stream().filter(cinema -> cinema.getId().equals(id))
                    .findFirst().orElseThrow());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
