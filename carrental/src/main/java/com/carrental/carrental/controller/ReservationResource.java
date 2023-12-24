package com.carrental.carrental.controller;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.service.CarService;
import com.carrental.carrental.service.OfficeService;
import com.carrental.carrental.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationResource {
    private final ReservationService reservationService;
    private final CarService carService;
    private final OfficeService officeService;

    public ReservationResource(ReservationService reservationService, CarService carService, OfficeService officeService) {
        this.reservationService = reservationService;
        this.carService = carService;
        this.officeService = officeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/find/id/{reservationId}")
    public ResponseEntity<Reservation> getReservationByReservationId(@PathVariable("reservationId") Integer reservationId) {
        Reservation reservation = reservationService.findReservationByReservationId(reservationId);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/find/date/{startDate}")
    public ResponseEntity<List<Reservation>> getReservationsByStartDate(@PathVariable("startDate") Date startDate) {
        List<Reservation> reservations = reservationService.findReservationsByStartDate(startDate);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/find/car/{plateId}")
    public ResponseEntity<List<Reservation>> getReservationsByCar(@PathVariable("plateId") Long plateId) {
        Car car = carService.findCarByPlateId(plateId);
        List<Reservation> reservations = reservationService.findReservationsByCar(car);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/find/office/{officeId}")
    public ResponseEntity<List<Reservation>> getReservationsByOffice(@PathVariable("officeId") Integer officeId) {
        Office office = officeService.findOfficeByOfficeId(officeId);
        List<Reservation> reservations = reservationService.findReservationsByOffice(office);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        Reservation newReservation = reservationService.addReservation(reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(reservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("delete/{reservationId}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable("reservationId") Integer reservationId) {
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
