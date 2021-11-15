package com.example.demo.services;

import com.example.demo.model.Cinema;
import com.example.demo.repository.RepositoryCinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCinema {
    @Autowired
    private RepositoryCinema methodsCrud;

    public List<Cinema> getAll(){
        return methodsCrud.getAll();
    }

    public Optional<Cinema> getCinema(int cinemaId) {
        return methodsCrud.getCinema(cinemaId);
    }

    public Cinema save(Cinema cinema){
        if(cinema.getId()==null){
            return methodsCrud.save(cinema);
        }else{
            Optional<Cinema> e= methodsCrud.getCinema(cinema.getId());
            if(e.isEmpty()){
                return methodsCrud.save(cinema);
            }else{
                return cinema;
            }
        }
    }

    public Cinema update(Cinema cinema){
        if(cinema.getId()!=null){
            Optional<Cinema> e= methodsCrud.getCinema(cinema.getId());
            if(!e.isEmpty()){
                if(cinema.getName()!=null){
                    e.get().setName(cinema.getName());
                }
                if(cinema.getOwner()!=null){
                    e.get().setOwner(cinema.getOwner());
                }
                if(cinema.getCapacity()!=null){
                    e.get().setCapacity(cinema.getCapacity());
                }
                if(cinema.getDescription()!=null){
                    e.get().setDescription(cinema.getDescription());
                }
                if(cinema.getCategory()!=null){
                    e.get().setCategory(cinema.getCategory());
                }
                methodsCrud.save(e.get());
                return e.get();
            }else{
                return cinema;
            }
        }else{
            return cinema;
        }
    }


    public boolean deleteCinema(int cinemaId) {
        Boolean aBoolean = getCinema(cinemaId).map(cinema -> {
            methodsCrud.delete(cinema);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
