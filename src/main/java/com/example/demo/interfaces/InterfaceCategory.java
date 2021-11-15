package com.example.demo.interfaces;

import com.example.demo.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceCategory extends CrudRepository<Category,Integer> {
}
