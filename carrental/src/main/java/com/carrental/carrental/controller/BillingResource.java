package com.carrental.carrental.controller;

import com.carrental.carrental.model.Billing;
import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.service.BillingService;
import com.carrental.carrental.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillingResource {
    private final BillingService billingService;
    private final ReservationService reservationService;

    public BillingResource(BillingService billingService, ReservationService reservationService) {
        this.billingService = billingService;
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Billing>> getAllBillings() {
        List<Billing> billings = billingService.findAllBillings();
        return new ResponseEntity<>(billings, HttpStatus.OK);
    }

    @GetMapping("/find/id/{billingId}")
    public ResponseEntity<Billing> getBillingByBillingId(@PathVariable("billingId") Integer billingId) {
        Billing billing = billingService.findBillingByBillingId(billingId);
        return new ResponseEntity<>(billing, HttpStatus.OK);
    }

    @GetMapping("/find/reservation/{reservationId}")
    public ResponseEntity<Billing> getBillingByReservationId(@PathVariable("reservationId") Integer reservationId) {
        Reservation reservation = reservationService.findReservationByReservationId(reservationId);
        Billing billing = billingService.findBillingByReservation(reservation);
        return new ResponseEntity<>(billing, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Billing> addBilling(@RequestBody Billing billing){
        Billing newBilling = billingService.addBilling(billing);
        return new ResponseEntity<>(newBilling, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Billing> updateBilling(@RequestBody Billing billing){
        Billing updateBilling = billingService.updateBilling(billing);
        return new ResponseEntity<>(updateBilling, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{billingId}")
    public ResponseEntity<?> deleteBilling(@PathVariable("billingId") Integer billingId){
        billingService.deleteBilling(billingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
