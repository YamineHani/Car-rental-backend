package com.carrental.carrental.controller;

import com.carrental.carrental.model.Billing;
import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.model.enums.CarStatus;
import com.carrental.carrental.service.BillingService;
import com.carrental.carrental.service.CarService;
import com.carrental.carrental.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@CrossOrigin("*")
public class ReservationResource {
    private final ReservationService reservationService;
    private final CarService carService;
    private final BillingService billingService;

    public ReservationResource(ReservationService reservationService, CarService carService, BillingService billingService) {
        this.reservationService = reservationService;
        this.carService = carService;
        this.billingService = billingService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllReservations() {
        return reservationService.findAllReservations();
    }

    @GetMapping("/find/id/{reservationId}")
    public ResponseEntity<?> getReservationByReservationId(@PathVariable("reservationId") Integer reservationId) {
        return reservationService.findReservationByReservationId(reservationId);
    }

    @GetMapping("/find/date/{startDate}")
    public ResponseEntity<?> getReservationsByStartDate(@PathVariable("startDate") Date startDate) {
        return reservationService.findReservationsByStartDate(startDate);
    }

    @GetMapping("/find/car/{plateId}")
    public ResponseEntity<?> getReservationsByPlateId(@PathVariable("plateId") Long plateId) {
        if(carService.findCarByPlateId(plateId).getStatusCode() == HttpStatus.FOUND)
        {
            Optional<Car> car = (Optional<Car>)carService.findCarByPlateId(plateId).getBody();
            if(car.isPresent())
            {
                return reservationService.findReservationsByCar(car.get());
            }
        }
        return new ResponseEntity<>("No car with plate id " + plateId + " was found", HttpStatus.NO_CONTENT);
    }

    /*@GetMapping("/find/office/{officeId}")
    public ResponseEntity<List<Reservation>> getReservationsByOfficeId(@PathVariable("officeId") Integer officeId) {
        List<Reservation> reservations = reservationService.findReservationsByOfficeId(officeId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }*/

    @PostMapping("/add")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        if(carService.findCarByPlateId(reservation.getPlateId()).getStatusCode() == HttpStatus.FOUND)
        {
            Optional<Car> car = (Optional<Car>)carService.findCarByPlateId(reservation.getPlateId()).getBody();
            if(car.get().getStatus() == CarStatus.ACTIVE)
            {
                car.get().setStatus(CarStatus.RENTED);
                return reservationService.addReservation(reservation);
            }
            return new ResponseEntity<>("Car selected is unavailable for now", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("No car with plate id " + reservation.getPlateId() + " was found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation) {
        if(reservationService.findReservationByReservationId(reservation.getReservationId()).getStatusCode() == HttpStatus.FOUND)
        {
            Optional<Reservation> reservationFound = (Optional<Reservation>)reservationService.findReservationByReservationId(reservation.getReservationId()).getBody();
            if(carService.findCarByPlateId(reservation.getPlateId()).getStatusCode() == HttpStatus.FOUND)
            {
                if(reservationFound.get().getPlateId().equals(reservation.getPlateId()))
                {
                    if(reservation.getEndDate() != null)
                    {
                        Car car = reservationFound.get().getCar();
                        car.setStatus(CarStatus.ACTIVE);
                        /*Billing billing = (Billing)billingService.findBillingByReservation(reservation).getBody();
                        billingService.updateBilling(billing);*/
                    }
                    return reservationService.updateReservation(reservation);
                }
                Optional<Car> carNew = (Optional<Car>)carService.findCarByPlateId(reservation.getPlateId()).getBody();
                Car carOld = reservationFound.get().getCar();
                if(carNew.get().getStatus() == CarStatus.ACTIVE)
                {
                    carNew.get().setStatus(CarStatus.RENTED);
                    carOld.setStatus(CarStatus.ACTIVE);
                    return reservationService.updateReservation(reservation);
                }
                return new ResponseEntity<>("Car selected is unavailable for now", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>("No car with plate id " + reservation.getPlateId() + " was found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("No reservation with id " + reservation.getReservationId() + " was found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable("reservationId") Integer reservationId) {
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}
