package com.example.demo.repository;

import com.example.demo.model.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.demo.interfaces.InterfaceCinema;

@Repository
public class RepositoryCinema {
    @Autowired
    private InterfaceCinema crud;

    public List<Cinema> getAll(){
        return (List<Cinema>) crud.findAll();
    }

    public Optional<Cinema> getCinema(int cinemaId){
        return crud.findById(cinemaId);
    }

    public Cinema save(Cinema cinema){
        return crud.save(cinema);
    }
    public void delete(Cinema cinema){
        crud.delete(cinema);
    }
}
