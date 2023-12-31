package com.carrental.carrental.controller;

import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.service.CarService;
import com.carrental.carrental.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reservations")
@AllArgsConstructor
@CrossOrigin("*")
public class ReservationResource {
    private final ReservationService reservationService;
    private final CarService carService;
    //private final BillingService billingService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllReservations() {
        return reservationService.findAllReservations();
    }

    @GetMapping("/find/id/{reservationId}")
    public ResponseEntity<?> getReservationByReservationId(
            @PathVariable("reservationId") Integer reservationId) {
        return reservationService.findReservationByReservationId(reservationId);
    }

//    @GetMapping("/find/date/{startDate}")
//    public ResponseEntity<?> getReservationsByStartDate(@PathVariable("startDate") Date startDate) {
//        return reservationService.findReservationsByStartDate(startDate);
//    }

    @GetMapping("/find/car/{plateId}")
    public ResponseEntity<?> getReservationsByPlateId(@PathVariable("plateId") Long plateId) {
        return reservationService.findReservationsByCar(plateId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

//    @PutMapping("/update")
//    public ResponseEntity<String> updateReservation(@RequestBody Reservation reservation) {
//        if(reservationService.findReservationByReservationId(reservation.getReservationId())
//                .getStatusCode() == HttpStatus.OK)
//        {
//            Optional<Reservation> reservationFound = (Optional<Reservation>)reservationService
//                    .findReservationByReservationId(reservation.getReservationId()).getBody();
//            if(carService.findCarByPlateId(reservation.getCar().getPlateId()).getStatusCode() == HttpStatus.OK)
//            {
//                if(reservationFound.get().getCar().getPlateId().equals(reservation.getCar().getPlateId()))
//                {
//                    if(reservation.getEndDate() != null)
//                    {
//                        Car car = reservationFound.get().getCar();
//                        car.setStatus(CarStatus.ACTIVE);
//                        /*Billing billing = (Billing)billingService.findBillingByReservation(reservation).getBody();
//                        billingService.updateBilling(billing);*/
//                    }
//                    return reservationService.updateReservation(reservation);
//                }
//                Optional<Car> carNew = (Optional<Car>)carService.findCarByPlateId(reservation.getCar().getPlateId()).getBody();
//                Car carOld = reservationFound.get().getCar();
//                if(carNew.get().getStatus() == CarStatus.ACTIVE)
//                {
//                    carNew.get().setStatus(CarStatus.RENTED);
//                    carOld.setStatus(CarStatus.ACTIVE);
//                    return reservationService.updateReservation(reservation);
//                }
//                return new ResponseEntity<>("Car selected is unavailable for now", HttpStatus.CONFLICT);
//            }
//            return new ResponseEntity<>("No car with plate id " + reservation.getCar().getPlateId() + " was found", HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>("No reservation with id " + reservation.getReservationId() + " was found", HttpStatus.NO_CONTENT);
//    }

    @DeleteMapping("delete/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable("reservationId") Integer reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
}
