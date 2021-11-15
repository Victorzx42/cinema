package com.example.demo.interfaces;

import com.example.demo.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceClient extends CrudRepository<Client,Integer> {
}
