package com.carrental.carrental.service;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.model.User;
import com.carrental.carrental.model.enums.CarStatus;
import com.carrental.carrental.repo.CarRepo;
import com.carrental.carrental.repo.ReservationRepo;
import com.carrental.carrental.repo.UserRepo;
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
    private final UserRepo userRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo, CarRepo carRepo, UserRepo userRepo) {
        this.reservationRepo = reservationRepo;
        this.carRepo = carRepo;
        this.userRepo = userRepo;
    }

    public ResponseEntity<?> addReservation(Reservation reservation) {
        if(carRepo.findCarByPlateId(reservation.getCar().getPlateId()).isPresent())
        {
            Optional<Car> car = carRepo.findCarByPlateId(reservation.getCar().getPlateId());
            if(car.get().getStatus() == CarStatus.ACTIVE)
            {
                car.get().setStatus(CarStatus.RENTED);
                reservationRepo.save(reservation);
                return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Car selected is unavailable for now", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("No car with plate id " + reservation.getCar().getPlateId() + " was found", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> findAllReservations() {
        List<Reservation> reservations = reservationRepo.findAll();
        if(reservations.isEmpty())
        {
            return new ResponseEntity<>("No reservations currently exist", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
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
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        }
        return new ResponseEntity<>("No reservation with id " + reservationId + " was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findReservationsByStartDate(Date startDate) {
        Optional<List<Reservation>> reservations = reservationRepo.findReservationsByStartDate(startDate);
        if(reservations.isPresent())
        {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        return new ResponseEntity<>("No reservation starting on " + startDate + " was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findReservationsByCar(Car car) {
        Optional<List<Reservation>> reservations = reservationRepo.findReservationsByCar(car);
        if(reservations.isPresent())
        {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        }
        return new ResponseEntity<>("No reservation for car with plate id " + car.getPlateId() + " was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findReservationsByUserId(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent())
        {
            Optional<List<Reservation>> reservations = reservationRepo.findReservationsByUser(user.get());
            if(reservations.isPresent())
            {
                return new ResponseEntity<>(reservations, HttpStatus.OK);
            }
            return new ResponseEntity<>("No reservations for user with id " + userId + " was found", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("No user with id " + userId + " was found", HttpStatus.UNAUTHORIZED);
    }
}
