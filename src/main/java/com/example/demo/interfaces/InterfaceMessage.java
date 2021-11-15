package com.example.demo.interfaces;

import com.example.demo.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceMessage extends CrudRepository<Message,Integer> {
}
