package com.example.demo.services;

import com.example.demo.model.ClientCount;
import com.example.demo.model.Reservation;
import com.example.demo.repository.RepositoryReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesReservation {
    @Autowired
    private RepositoryReservation methodsCrud;

    public List<Reservation> getAll(){
        return methodsCrud.getAll();
    }

    public Optional<Reservation> getReservation(int reservationId) {
        return methodsCrud.getReservation(reservationId);
    }

    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation()==null){
            return methodsCrud.save(reservation);
        }else{
            Optional<Reservation> e= methodsCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return methodsCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= methodsCrud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                methodsCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            methodsCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public StatusReservation reporteStatusServicio (){
        List<Reservation>completed= methodsCrud.ReservacionStatusRepositorio("completed");
        List<Reservation>cancelled= methodsCrud.ReservacionStatusRepositorio("cancelled");

        return new StatusReservation(completed.size(), cancelled.size() );
    }

    public List<Reservation> reporteTiempoServicio (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");

        Date datoUno = new Date();
        Date datoDos = new Date();

        try{
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return methodsCrud.ReservacionTiempoRepositorio(datoUno, datoDos);
        }else{
            return new ArrayList<>();

        }
    }
    public List<ClientCount> reporteClientesServicio(){
        return methodsCrud.getRepositoryClient();
    }
}
