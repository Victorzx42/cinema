package com.example.demo.interfaces;

import com.example.demo.model.Cinema;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceCinema extends CrudRepository<Cinema,Integer> {
}
