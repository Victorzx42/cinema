package com.example.demo.web;


import com.example.demo.model.Cinema;
import com.example.demo.services.ServiceCinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/Cinema")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ControllerCinemas {
    @Autowired
    private ServiceCinema service;
    @GetMapping("/all")
    public List<Cinema> getCinemas(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Cinema> getCinema(@PathVariable("id") int cinemaId) {
        return service.getCinema(cinemaId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cinema save(@RequestBody Cinema cinema) {
        return service.save(cinema);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Cinema update(@RequestBody Cinema cinema) {
        return service.update(cinema);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int cinemaId) {
        return service.deleteCinema(cinemaId);
    }
}
