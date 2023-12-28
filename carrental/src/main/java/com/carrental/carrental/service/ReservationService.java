package com.carrental.carrental.service;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.repo.CarRepo;
import com.carrental.carrental.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final CarRepo carRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo, CarRepo carRepo) {
        this.reservationRepo = reservationRepo;
        this.carRepo = carRepo;
    }

    public ResponseEntity<?> addReservation(Reservation reservation) {
        reservationRepo.save(reservation);
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    public ResponseEntity<?> findAllReservations() {
        List<Reservation> reservations = reservationRepo.findAll();
        if(reservations.isEmpty())
        {
            return new ResponseEntity<>("No reservations currently exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reservations, HttpStatus.FOUND);
    }

    public ResponseEntity<?> updateReservation(Reservation reservation) {
        reservationRepo.save(reservation);
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

    @Transactional
    public void deleteReservation(Integer reservationId) {
        reservationRepo.deleteReservationByReservationId(reservationId);
    }

    public ResponseEntity<?> findReservationByReservationId(Integer reservationId) {
        Optional<Reservation> reservation = reservationRepo.findReservationByReservationId(reservationId);
        if(reservation.isPresent())
        {
            return new ResponseEntity<>(reservation, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("No reservation with id " + reservationId + " was found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findReservationsByStartDate(Date startDate) {
        Optional<List<Reservation>> reservations = reservationRepo.findReservationsByStartDate(startDate);
        if(reservations.isPresent())
        {
            return new ResponseEntity<>(reservations, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("No reservation starting on " + startDate + " was found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findReservationsByCar(Car car) {
        Optional<List<Reservation>> reservations = reservationRepo.findReservationsByCar(car);
        if(reservations.isPresent())
        {
            return new ResponseEntity<>(reservations, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("No reservation for car with plate id " + car.getPlateId() + " was found", HttpStatus.NOT_FOUND);
    }
}
