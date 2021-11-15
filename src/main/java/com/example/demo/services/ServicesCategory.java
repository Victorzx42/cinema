package com.example.demo.services;

import com.example.demo.model.Category;
import com.example.demo.repository.RepositoryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesCategory {
    @Autowired
    private RepositoryCategory methodsCrud;

    public List<Category> getAll() {
        return methodsCrud.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return methodsCrud.getCategory(categoryId);
    }

    public Category save(Category category) {
        if (category.getId()== null) {
            return methodsCrud.save(category);
        } else {
            Optional<Category> e= methodsCrud.getCategory(category.getId());
            if (e.isEmpty()) {
                return methodsCrud.save(category);
            } else {
                return category;
            }
        }
    }

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g= methodsCrud.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return methodsCrud.save(g.get());
            }
        }
        return category;
    }
    public boolean deletecategory(int categoryId){
        Boolean d=getCategory(categoryId).map(category -> {
            methodsCrud.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
}
